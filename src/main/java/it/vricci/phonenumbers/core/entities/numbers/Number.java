package it.vricci.phonenumbers.core.entities.numbers;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.util.regex.Pattern;

@Value(staticConstructor = "of")
public class Number {
    @Getter(value = AccessLevel.PRIVATE)
    String value;

    boolean matches(Pattern pattern) {
        return pattern
                .matcher(getValue())
                .matches();
    }

    @Override
    public String toString() {
        return value;
    }
}