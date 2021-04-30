package it.vricci.phonenumbers.core.entities.numbers;

import it.vricci.phonenumbers.core.entities.checkers.SouthAfricanMobileCheckStatus;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
class TelephoneNumberCorrected implements TelephoneNumber {
    private final SouthAfricanMobileCheckStatus checkStatus;
    private final TelephoneNumber original;
    private final Number corrected;

    protected TelephoneNumberCorrected(TelephoneNumber original, SouthAfricanMobileCheckStatus checkStatus, String corrected) {
        this.corrected = Number.of(corrected);
        this.checkStatus = checkStatus;
        this.original =original;
    }

    @Override
    public String getOriginalNumber() {
        return original.getOriginalNumber();
    }

    public String getCorrectedNumber() {
        return corrected.toString();
    }

    @Override
    public boolean isCorrect() {
        return false;
    }

    @Override
    public boolean isIncorrect() {
        return false;
    }

    @Override
    public boolean isCorrected() {
        return true;
    }

    @Override
    public boolean matches(String regex) {
        throw new RuntimeException();
    }

    @Override
    public String getId() {
        return original.getId();
    }

    @Override
    public String getCheckStatus() {
        return checkStatus.toString();
    }

    @Override
    public String toString() {
        return "Corrected: "+getCorrectedNumber()+" cause "+getCheckStatus()+"\n" +
                "(original:   " + getOriginalNumber()+")\n";
    }

}
