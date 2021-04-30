package it.vricci.phonenumbers.core.usecases.showResults;

import it.vricci.phonenumbers.core.usecases.BusinessException;

public interface GetResultsUseCase {
    GetResultsUseCaseResponse act() throws BusinessException;
}
