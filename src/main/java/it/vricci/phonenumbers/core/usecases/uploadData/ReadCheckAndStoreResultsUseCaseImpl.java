package it.vricci.phonenumbers.core.usecases.uploadData;

import it.vricci.phonenumbers.core.entities.numbers.GroupOfNumbers;
import it.vricci.phonenumbers.core.usecases.BusinessException;
import it.vricci.phonenumbers.core.usecases.NumbersChecker;
import it.vricci.phonenumbers.core.usecases.PhoneNumbersRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReadCheckAndStoreResultsUseCaseImpl implements ReadCheckAndStoreResultsUseCase {
    private final NumbersDataParser parser;
    private final NumbersChecker checker;
    private final PhoneNumbersRepository repository;

    public ReadCheckAndStoreResultsUseCaseImpl(NumbersDataParser parser, NumbersChecker checker, PhoneNumbersRepository repository) {
        this.parser = parser;
        this.checker = checker;
        this.repository = repository;
    }

    @Override
    public ReadCheckAndStoreResultsUseCaseResponse act(ReadCheckAndStoreResultsRequest request) throws BusinessException {
        final GroupOfNumbers groupOfNumbers = readData(request);
        final GroupOfNumbers checkedGroupOfNumbers = checkNumbers(groupOfNumbers);
        final GroupOfNumbers savedResults = saveResults(checkedGroupOfNumbers);
        final ReadCheckAndStoreResultsUseCaseResponse response = ReadCheckAndStoreResultsUseCaseResponse
                .success(savedResults);
        log.info("Response {}",response);
        return response;
    }

    private GroupOfNumbers readData(ReadCheckAndStoreResultsRequest request) throws BusinessException {
        final GroupOfNumbers groupOfNumbers;
        try {
            groupOfNumbers = parser.parse(request);
        }catch (Throwable throwable){
            log.error("Error reading data from {}", request,throwable);
            throw BusinessException.readDataException();
        }
        if(groupOfNumbers.isEmpty()) {
            throw BusinessException.emptyDataException();
        }
        return groupOfNumbers;
    }

    private GroupOfNumbers checkNumbers(GroupOfNumbers groupOfNumbers) throws BusinessException {
        try {
            return groupOfNumbers.check(checker);
        }catch (Throwable throwable){
            log.error("Error checking groupOfNumbers {}", groupOfNumbers, throwable);
            throw BusinessException.checkNumbersException();
        }
    }

    private GroupOfNumbers saveResults(GroupOfNumbers groupOfNumbers) throws BusinessException {
        try {
            return repository.save(groupOfNumbers);
        }catch (Throwable throwable){
            log.error("Error saving groupOfNumbers {}", groupOfNumbers, throwable);
            throw BusinessException.saveResultsException();
        }
    }
}
