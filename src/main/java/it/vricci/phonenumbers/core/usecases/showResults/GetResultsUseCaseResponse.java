package it.vricci.phonenumbers.core.usecases.showResults;

import it.vricci.phonenumbers.core.entities.numbers.GroupOfNumbers;
import lombok.Data;

@Data
public class GetResultsUseCaseResponse {
    private final GroupOfNumbers groupOfNumbers;

    public static GetResultsUseCaseResponse of(GroupOfNumbers groupOfNumbers) {
        return new GetResultsUseCaseResponse(groupOfNumbers);
    }
}
