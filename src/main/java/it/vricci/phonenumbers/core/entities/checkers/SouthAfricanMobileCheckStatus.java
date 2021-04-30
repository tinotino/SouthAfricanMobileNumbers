package it.vricci.phonenumbers.core.entities.checkers;

import it.vricci.phonenumbers.core.entities.numbers.TelephoneNumber;
import it.vricci.phonenumbers.core.entities.numbers.TelephoneNumberFactory;

public enum SouthAfricanMobileCheckStatus {
    UNCHECKED {
        @Override
        public boolean isCorrected() {
            return false;
        }

        @Override
        public boolean canHandle(TelephoneNumber telephoneNumber) {
            return false;
        }

        @Override
        public TelephoneNumber handle(TelephoneNumber telephoneNumber) {
            return telephoneNumber;
        }
    },
    CORRECT {
        @Override
        public boolean isCorrected() {
            return false;
        }

        @Override
        public boolean canHandle(TelephoneNumber telephoneNumber) {
            return telephoneNumber.matches(COUNTRY_CODE + MOBILE_AREA_CODE + TELEPHONE_NUMBER);
        }

        @Override
        public TelephoneNumber handle(TelephoneNumber telephoneNumber) {
            return TelephoneNumberFactory.correct(telephoneNumber);
        }
    },
    WRONG_COUNTRY_CODE {
        @Override
        public boolean isCorrected() {
            return true;
        }

        @Override
        public boolean canHandle(TelephoneNumber telephoneNumber) {
            return telephoneNumber.matches(ANY_PREFIX + MOBILE_AREA_CODE + TELEPHONE_NUMBER);
        }

        @Override
        public TelephoneNumber handle(TelephoneNumber telephoneNumber) {
            return TelephoneNumberFactory.corrected(this, telephoneNumber, COUNTRY_CODE + telephoneNumber.getOriginalNumber().substring(2));
        }
    },
    MISSING_COUNTRY_CODE {
        @Override
        public boolean isCorrected() {
            return true;
        }

        @Override
        public boolean canHandle(TelephoneNumber telephoneNumber) {
            return telephoneNumber.matches(MOBILE_AREA_CODE + TELEPHONE_NUMBER);
        }

        @Override
        public TelephoneNumber handle(TelephoneNumber telephoneNumber) {
            return TelephoneNumberFactory.corrected(this, telephoneNumber, COUNTRY_CODE + telephoneNumber.getOriginalNumber());
        }
    },
    TOO_LONG_PHONE_NUMBER {
        @Override
        public boolean isCorrected() {
            return true;
        }

        @Override
        public boolean canHandle(TelephoneNumber telephoneNumber) {
            return telephoneNumber.matches(COUNTRY_CODE + MOBILE_AREA_CODE + TELEPHONE_NUMBER + SOMETHING);
        }

        @Override
        public TelephoneNumber handle(TelephoneNumber telephoneNumber) {
            return TelephoneNumberFactory.corrected(this, telephoneNumber, telephoneNumber.getOriginalNumber().substring(0,11));
        }
    },
    INCORRECT {
        @Override
        public boolean isCorrected() {
            return false;
        }

        @Override
        public boolean canHandle(TelephoneNumber telephoneNumber) {
            return true;
        }

        @Override
        public TelephoneNumber handle(TelephoneNumber telephoneNumber) {
            return TelephoneNumberFactory.incorrect(telephoneNumber);
        }
    };
    public static final String ANY_PREFIX = "\\d{2}";
    public static final String SOMETHING = ".*";
    private static final String TELEPHONE_NUMBER = "\\d{7}";
    private static final String MOBILE_AREA_CODE = "(6\\d|7[0-3]|7[5-9]|8[1-4])";
    private static final String COUNTRY_CODE = "27";

    public static SouthAfricanMobileCheckStatus fromName(String name) {
        return SouthAfricanMobileCheckStatus.valueOf(name);
    }

    public abstract boolean isCorrected();

    public abstract boolean canHandle(TelephoneNumber telephoneNumber);

    public abstract TelephoneNumber handle(TelephoneNumber telephoneNumber);
}
