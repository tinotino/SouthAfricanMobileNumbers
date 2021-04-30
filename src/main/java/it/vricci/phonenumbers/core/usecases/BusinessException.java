package it.vricci.phonenumbers.core.usecases;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = false)
@ToString
public class BusinessException extends Throwable{
    private final BusinessExceptionEnum exception;

    public BusinessException(BusinessExceptionEnum exception) {
        this.exception = exception;
    }

    public static BusinessException readDataException() {
        return new BusinessException(BusinessExceptionEnum.READ_DATA_EXCEPTION);
    }

    public static BusinessException checkNumbersException() {
        return new BusinessException(BusinessExceptionEnum.CHECK_NUMBERS_EXCEPTION);
    }

    public static BusinessException saveResultsException() {
        return new BusinessException(BusinessExceptionEnum.SAVE_RESULTS_EXCEPTION);
    }

    public static BusinessException emptyDataException() {
        return new BusinessException(BusinessExceptionEnum.EMPTY_DATA_EXCEPTION);
    }

    public static BusinessException readResultsException() {
        return new BusinessException(BusinessExceptionEnum.READ_RESULTS_EXCEPTION);
    }

}
