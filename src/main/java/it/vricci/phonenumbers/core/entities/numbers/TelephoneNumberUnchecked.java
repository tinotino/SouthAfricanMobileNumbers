package it.vricci.phonenumbers.core.entities.numbers;

import it.vricci.phonenumbers.core.entities.checkers.SouthAfricanMobileCheckStatus;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
class TelephoneNumberUnchecked implements TelephoneNumber {
    private final SouthAfricanMobileCheckStatus checkStatus;
    private final NumberRecord numberRecord;

    protected TelephoneNumberUnchecked(String id, String number) {
        this.numberRecord= new NumberRecord(id, Number.of(number));
        this.checkStatus = SouthAfricanMobileCheckStatus.UNCHECKED;
    }

    @Override
    public String getOriginalNumber() {
        return numberRecord.getNumber().toString();
    }

    @Override
    public String getCorrectedNumber() {
        return null;
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
        return false;
    }

    @Override
    public boolean matches(String regex) {
        return numberRecord.matches(regex);
    }

    @Override
    public String getId() {
        return numberRecord.getId();
    }

    @Override
    public String getCheckStatus() {
        return SouthAfricanMobileCheckStatus.UNCHECKED.name();
    }

    @Override
    public String toString() {
        return "Unchecked: " + getOriginalNumber()+"\n";
    }
}
