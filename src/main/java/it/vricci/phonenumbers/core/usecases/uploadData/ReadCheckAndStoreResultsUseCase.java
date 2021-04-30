package it.vricci.phonenumbers.core.usecases.uploadData;

import it.vricci.phonenumbers.core.usecases.BusinessException;

public interface ReadCheckAndStoreResultsUseCase {
    ReadCheckAndStoreResultsUseCaseResponse act(ReadCheckAndStoreResultsRequest request) throws BusinessException;
}
