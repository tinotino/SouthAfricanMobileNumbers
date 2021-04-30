package it.vricci.phonenumbers.core.usecases.checkNumber;

import it.vricci.phonenumbers.core.entities.numbers.GroupOfNumbers;
import it.vricci.phonenumbers.core.entities.numbers.TelephoneNumberFactory;
import it.vricci.phonenumbers.core.usecases.BusinessException;
import it.vricci.phonenumbers.core.usecases.NumbersChecker;
import it.vricci.phonenumbers.core.usecases.PhoneNumbersRepository;
import it.vricci.phonenumbers.core.usecases.uploadData.NumbersDataParser;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CheckNumberUseCaseImpl implements CheckNumberUseCase {
    private final NumbersChecker checker;

    public CheckNumberUseCaseImpl(NumbersChecker checker) {
        this.checker = checker;
    }

    @Override
    public CheckNumberResult check(String number) throws BusinessException {
        final GroupOfNumbers checkedGroupOfNumbers = checkNumbers(GroupOfNumbers.with(TelephoneNumberFactory.unchecked("id",number)));
        return CheckNumberResult.from(checkedGroupOfNumbers);
    }


    private GroupOfNumbers checkNumbers(GroupOfNumbers groupOfNumbers) throws BusinessException {
        try {
            return groupOfNumbers.check(checker);
        }catch (Throwable throwable){
            log.error("Error checking groupOfNumbers {}", groupOfNumbers, throwable);
            throw BusinessException.checkNumbersException();
        }
    }
}
