package it.vricci.phonenumbers.dataproviders.db;

import it.vricci.phonenumbers.core.entities.numbers.GroupOfNumbers;
import it.vricci.phonenumbers.core.usecases.PhoneNumbersRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
public class PhoneNumbersDbRepository implements PhoneNumbersRepository {
    private final PhoneNumberCrudRepository phoneNumberCrudRepository;

    public PhoneNumbersDbRepository(PhoneNumberCrudRepository phoneNumberCrudRepository) {
        this.phoneNumberCrudRepository = phoneNumberCrudRepository;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public GroupOfNumbers save(GroupOfNumbers checkedGroupOfNumbers) {
        List<PhoneNumberEntity> list = from(checkedGroupOfNumbers);

        final List<PhoneNumberEntity> entities = list.stream()
                .map(phoneNumberCrudRepository::save)
                .collect(Collectors.toList());

        return toPhoneNumbers(entities);
    }

    @Override
    public GroupOfNumbers findAll() {
        return toPhoneNumbers(phoneNumberCrudRepository.findAll());
    }

    public static List<PhoneNumberEntity> from(GroupOfNumbers groupOfNumbers) {
        return groupOfNumbers.getList().stream()
                .map(PhoneNumberEntity::from)
                .collect(Collectors.toList());
    }

    private GroupOfNumbers toPhoneNumbers(List<PhoneNumberEntity> entities) {
        return GroupOfNumbers.with(entities.stream()
                        .map(PhoneNumberEntity::toPhoneNumber)
                        .collect(Collectors.toList()));
    }
}
