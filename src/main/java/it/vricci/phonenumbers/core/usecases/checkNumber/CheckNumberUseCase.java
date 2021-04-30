package it.vricci.phonenumbers.core.usecases.checkNumber;

import it.vricci.phonenumbers.core.usecases.BusinessException;

public interface CheckNumberUseCase {
    CheckNumberResult check(String number) throws BusinessException;
}
