package it.vricci.phonenumbers.core.usecases.showResults;

import it.vricci.phonenumbers.core.entities.numbers.GroupOfNumbers;
import it.vricci.phonenumbers.core.usecases.BusinessException;
import it.vricci.phonenumbers.core.usecases.PhoneNumbersRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GetResultsUseCaseImpl implements GetResultsUseCase {
    private final PhoneNumbersRepository repository;

    public GetResultsUseCaseImpl(PhoneNumbersRepository repository) {
        this.repository = repository;
    }

    @Override
    public GetResultsUseCaseResponse act() throws BusinessException {
        final GroupOfNumbers results = findResults();
        return GetResultsUseCaseResponse.of(results);
    }

    private GroupOfNumbers findResults() throws BusinessException {
        try {
            return repository.findAll();
        }catch (Throwable throwable){
            log.error("Error reading results", throwable);
            throw BusinessException.readResultsException();
        }
    }
}
