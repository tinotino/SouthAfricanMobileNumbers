package it.vricci.phonenumbers.core.usecases;

import it.vricci.phonenumbers.core.entities.numbers.TelephoneNumber;

public interface NumbersChecker {
    TelephoneNumber check(TelephoneNumber telephoneNumber);
}
