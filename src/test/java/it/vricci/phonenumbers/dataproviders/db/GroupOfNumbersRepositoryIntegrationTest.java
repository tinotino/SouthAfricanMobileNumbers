package it.vricci.phonenumbers.dataproviders.db;

import it.vricci.phonenumbers.MockFactory;
import it.vricci.phonenumbers.core.entities.numbers.GroupOfNumbers;
import it.vricci.phonenumbers.core.entities.numbers.TelephoneNumber;
import it.vricci.phonenumbers.core.usecases.PhoneNumbersRepository;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest
class TelephoneNumbersRepositoryIntegrationTest {
    private final MockFactory mockFactory = new MockFactory();
    @Autowired
    private PhoneNumbersRepository repository;
    @Autowired
    private PhoneNumberCrudRepository crudRepository;

    @Test
    public void save_success() {
        assertRecordSavedAndReturned(with(mockFactory.anUnchecked()));
        assertRecordSavedAndReturned(with(mockFactory.aCorrected()));
        assertRecordSavedAndReturned(with(mockFactory.anIncorrect()));
        assertRecordSavedAndReturned(with(mockFactory.aCorrect()));
    }

    private void assertRecordSavedAndReturned(GroupOfNumbers groupOfNumbers) {
        crudRepository.deleteAll();

        final GroupOfNumbers saved = repository.save(groupOfNumbers);

        assertThat(crudRepository.count(), Is.is(1L));
        assertThat(saved, Is.is(groupOfNumbers));
    }

    private GroupOfNumbers with(TelephoneNumber telephoneNumber) {
        return GroupOfNumbers.with(telephoneNumber);
    }

    @Test
    public void findAll_success() {
        final GroupOfNumbers groupOfNumbers = GroupOfNumbers.with(mockFactory.anIncorrect(), mockFactory.aCorrect(), mockFactory.aCorrected(), mockFactory.anUnchecked());
        crudRepository.deleteAll();
        repository.save(groupOfNumbers);

        final GroupOfNumbers all = repository.findAll();

        assertThat(all, Is.is(groupOfNumbers));
    }

}