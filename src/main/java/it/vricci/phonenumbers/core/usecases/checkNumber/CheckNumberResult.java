package it.vricci.phonenumbers.core.usecases.checkNumber;

import it.vricci.phonenumbers.core.entities.numbers.GroupOfNumbers;
import it.vricci.phonenumbers.core.entities.numbers.TelephoneNumber;
import lombok.Data;

@Data
public class CheckNumberResult {
    private final String result;

    public static CheckNumberResult from(GroupOfNumbers groupOfNumbers) {
        final TelephoneNumber telephoneNumber = groupOfNumbers.getList().get(0);
        return new CheckNumberResult(telephoneNumber.toString());
    }

    @Override
    public String toString() {
        return result;
    }
}
