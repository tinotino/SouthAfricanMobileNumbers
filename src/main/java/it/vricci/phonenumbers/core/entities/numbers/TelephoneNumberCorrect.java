package it.vricci.phonenumbers.core.entities.numbers;

import it.vricci.phonenumbers.core.entities.checkers.SouthAfricanMobileCheckStatus;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
class TelephoneNumberCorrect implements TelephoneNumber {
    private final TelephoneNumber telephoneNumber;

    protected TelephoneNumberCorrect(TelephoneNumber telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    @Override
    public String getOriginalNumber() {
        return telephoneNumber.getOriginalNumber();
    }

    @Override
    public String getCorrectedNumber() {
        return null;
    }

    @Override
    public boolean isCorrect() {
        return true;
    }

    @Override
    public boolean isIncorrect() {
        return false;
    }

    @Override
    public boolean isCorrected() {
        return false;
    }

    @Override
    public boolean matches(String regex) {
        throw new RuntimeException();
    }

    @Override
    public String getId() {
        return telephoneNumber.getId();
    }

    @Override
    public String getCheckStatus() {
        return SouthAfricanMobileCheckStatus.CORRECT.name();
    }

    @Override
    public String toString() {
        return "Correct:   " + getOriginalNumber()+"\n";
    }
}
