package it.vricci.phonenumbers.core.entities.numbers;

import lombok.Data;

import java.util.regex.Pattern;

@Data
public class NumberRecord {
    private final String id;
    private final Number number;

    boolean matches(String regex) {
        return getNumber().matches(Pattern.compile(regex));
    }
}