package it.vricci.phonenumbers.core.usecases.uploadData;

import it.vricci.phonenumbers.core.entities.numbers.GroupOfNumbers;
import lombok.Data;

@Data
public class ReadCheckAndStoreResultsUseCaseResponse {
    private final GroupOfNumbers results;

    private ReadCheckAndStoreResultsUseCaseResponse(GroupOfNumbers results) {
        this.results = results;
    }

    public static ReadCheckAndStoreResultsUseCaseResponse success(GroupOfNumbers checkedGroupOfNumbers) {
        return new ReadCheckAndStoreResultsUseCaseResponse(checkedGroupOfNumbers);
    }

    public static ReadCheckAndStoreResultsUseCaseResponse empty() {
        return new ReadCheckAndStoreResultsUseCaseResponse(GroupOfNumbers.empty());
    }
}
