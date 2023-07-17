package com.nisum.apiusers.repository;

import com.nisum.apiusers.entities.Phone;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneRepository extends CrudRepository<Phone, String> {
}
