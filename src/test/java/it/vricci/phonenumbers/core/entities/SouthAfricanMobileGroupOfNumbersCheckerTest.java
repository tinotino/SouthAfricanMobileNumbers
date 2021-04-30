package it.vricci.phonenumbers.core.entities;

import it.vricci.phonenumbers.core.entities.checkers.SouthAfricanMobileNumbersChecker;
import it.vricci.phonenumbers.core.entities.checkers.SouthAfricanMobileCheckStatus;
import it.vricci.phonenumbers.core.entities.numbers.TelephoneNumber;
import it.vricci.phonenumbers.core.entities.numbers.TelephoneNumberFactory;
import it.vricci.phonenumbers.core.usecases.NumbersChecker;
import org.junit.jupiter.api.Test;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

class SouthAfricanMobileGroupOfNumbersCheckerTest {

    private final NumbersChecker checker = new SouthAfricanMobileNumbersChecker();

    @Test
    public void acceptableNumbers(){
        asList("60","61","62","63","64","65","66","66","68","69")
                .forEach(this::assertCorrectWellFormedNumbersWithPrefix);
        asList("70","71","72","73","75","76","77","78","79")
                .forEach(this::assertCorrectWellFormedNumbersWithPrefix);
        asList("81","82","83","84")
                .forEach(this::assertCorrectWellFormedNumbersWithPrefix);
    }

    private void assertCorrectWellFormedNumbersWithPrefix(String prefix) {
        TelephoneNumber telephoneNumber = TelephoneNumberFactory.unchecked("id", "27" + prefix + "1234567");
        final TelephoneNumber actual = checker.check(telephoneNumber);

        assertThat(actual, is(TelephoneNumberFactory.correct(telephoneNumber)));
    }

    @Test
    public void incorrectNumbers(){
        assertIncorrectNumber("7845");
        assertIncorrectNumber("27861234567");
        assertIncorrectNumber("278467");
        assertIncorrectNumber("27851234567");
    }

    private void assertIncorrectNumber(String number) {
        final TelephoneNumber unchecked = TelephoneNumberFactory.unchecked("id", number);

        final TelephoneNumber actual = checker.check(unchecked);

        assertThat(actual, is(TelephoneNumberFactory.incorrect(unchecked)));
    }

    @Test
    public void verifyCorrectableCheckStatusAndCorrectedNumber(){
        checkCorrectableType(SouthAfricanMobileCheckStatus.MISSING_COUNTRY_CODE, "831234567","27831234567");
        checkCorrectableType(SouthAfricanMobileCheckStatus.WRONG_COUNTRY_CODE, "25831234567","27831234567");
        checkCorrectableType(SouthAfricanMobileCheckStatus.TOO_LONG_PHONE_NUMBER, "27831234567_too_long","27831234567");
    }

    private void checkCorrectableType(SouthAfricanMobileCheckStatus missingCountryCode, String original, String corrected) {
        final TelephoneNumber telephoneNumber = TelephoneNumberFactory.unchecked("1", original);

        final TelephoneNumber actual = checker.check(telephoneNumber);

        assertThat(actual.getCheckStatus(), is(missingCountryCode.name()));
        assertThat(actual.getCorrectedNumber(), is(corrected));
    }
}