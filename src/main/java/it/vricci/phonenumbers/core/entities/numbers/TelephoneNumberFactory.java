package it.vricci.phonenumbers.core.entities.numbers;

import it.vricci.phonenumbers.core.entities.checkers.SouthAfricanMobileCheckStatus;

public class TelephoneNumberFactory {
    public static TelephoneNumber unchecked(String id, String number) {
        return new TelephoneNumberUnchecked(id, number);
    }

    public static TelephoneNumber correct(String id, String number) {
        return correct(unchecked(id, number));
    }

    public static TelephoneNumber correct(TelephoneNumber telephoneNumber) {
        return new TelephoneNumberCorrect(telephoneNumber);
    }

    public static TelephoneNumber incorrect(TelephoneNumber telephoneNumber) {
        return new TelephoneNumberIncorrect(telephoneNumber);
    }

    public static TelephoneNumberCorrected corrected(SouthAfricanMobileCheckStatus checkStatus, TelephoneNumber original, String corrected) {
        return new TelephoneNumberCorrected(original, checkStatus, corrected);
    }

    public static TelephoneNumber from(String checkStatusName, String externalId, String originalNumber, String correctedNumber) {
        final TelephoneNumber original = unchecked(externalId, originalNumber);
        final SouthAfricanMobileCheckStatus checkStatus = SouthAfricanMobileCheckStatus.fromName(checkStatusName);
        if (checkStatus.isCorrected()) {
            return corrected(checkStatus, original, correctedNumber);
        }
        if (checkStatus.equals(SouthAfricanMobileCheckStatus.CORRECT)) {
            return correct(original);
        }
        if (checkStatus.equals(SouthAfricanMobileCheckStatus.INCORRECT)) {
            return incorrect(original);
        }
        if (checkStatus.equals(SouthAfricanMobileCheckStatus.UNCHECKED)) {
            return original;
        }
        throw new RuntimeException("Unknown type");
    }
}
