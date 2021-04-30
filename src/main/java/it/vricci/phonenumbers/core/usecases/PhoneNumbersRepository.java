package it.vricci.phonenumbers.core.usecases;

import it.vricci.phonenumbers.core.entities.numbers.GroupOfNumbers;

public interface PhoneNumbersRepository {
    GroupOfNumbers save(GroupOfNumbers checkedGroupOfNumbers);
    GroupOfNumbers findAll();
}
