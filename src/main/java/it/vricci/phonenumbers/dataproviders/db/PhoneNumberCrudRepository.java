package it.vricci.phonenumbers.dataproviders.db;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PhoneNumberCrudRepository extends CrudRepository<PhoneNumberEntity,Long> {
    List<PhoneNumberEntity> findAll();
}
