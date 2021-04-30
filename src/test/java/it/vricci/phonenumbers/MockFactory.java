package it.vricci.phonenumbers;

import it.vricci.phonenumbers.core.entities.checkers.SouthAfricanMobileCheckStatus;
import it.vricci.phonenumbers.core.entities.numbers.TelephoneNumberFactory;
import it.vricci.phonenumbers.core.entities.numbers.TelephoneNumber;

public class MockFactory {
    public MockFactory() {
    }

    public TelephoneNumber anIncorrect() {
        return TelephoneNumberFactory.incorrect(TelephoneNumberFactory.unchecked("1", "incorrect"));
    }

    public TelephoneNumber aCorrect() {
        return TelephoneNumberFactory.correct("2", "27831234567");
    }

    public TelephoneNumber anUnchecked() {
        return TelephoneNumberFactory.unchecked("3", "1234");
    }

    public TelephoneNumber aCorrected() {
        return TelephoneNumberFactory.corrected(SouthAfricanMobileCheckStatus.MISSING_COUNTRY_CODE, TelephoneNumberFactory.unchecked("4","831234567"), "27831234567");
    }
}