package it.vricci.phonenumbers.entrypoints;

import it.vricci.phonenumbers.core.entities.numbers.GroupOfNumbers;
import it.vricci.phonenumbers.core.usecases.BusinessException;
import it.vricci.phonenumbers.core.usecases.uploadData.ReadCheckAndStoreResultsUseCaseResponse;
import lombok.Data;

@Data
public class UploadResponseDto {
    private final BusinessException businessException;
    private final Integer countTotal;
    private final Integer countCorrect;
    private final Integer countIncorrect;
    private final Integer countCorrected;

    public UploadResponseDto(GroupOfNumbers results, BusinessException businessException) {
        this.businessException = businessException;
        this.countTotal = results.countTotal();
        this.countCorrect = results.countCorrect();
        this.countIncorrect = results.countIncorrect();
        this.countCorrected = results.countCorrected();
    }

    public static UploadResponseDto success(ReadCheckAndStoreResultsUseCaseResponse response) {
        return new UploadResponseDto(response.getResults(), null);
    }

    public static UploadResponseDto failure(BusinessException businessException) {
        return new UploadResponseDto(GroupOfNumbers.empty(), businessException);
    }
}
