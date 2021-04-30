package it.vricci.phonenumbers.entrypoints;

import it.vricci.phonenumbers.core.entities.numbers.GroupOfNumbers;
import it.vricci.phonenumbers.core.usecases.BusinessException;
import it.vricci.phonenumbers.core.usecases.showResults.GetResultsUseCaseResponse;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class GetResultsResponseDto {
    private final List<PhoneNumbersDto> results;
    private final BusinessException businessException;

    public static GetResultsResponseDto success(GetResultsUseCaseResponse response) {
        return new GetResultsResponseDto(toPhoneNumbersDto(response.getGroupOfNumbers()),null);
    }

    public static GetResultsResponseDto failure(BusinessException businessException) {
        return new GetResultsResponseDto(null,businessException);
    }

    private static List<PhoneNumbersDto> toPhoneNumbersDto(GroupOfNumbers groupOfNumbers) {
        return groupOfNumbers.getList().stream()
                .map(PhoneNumbersDto::from)
                .collect(Collectors.toList());
    }
}
