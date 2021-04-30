package it.vricci.phonenumbers.core.entities.numbers;

public interface TelephoneNumber {
    String getOriginalNumber();

    String getCorrectedNumber();

    boolean isCorrect();

    boolean isIncorrect();

    boolean isCorrected();

    boolean matches(String regex);

    String getId();

    String getCheckStatus();
}
