package com.archetype.api.infraestructure.repository;

import com.archetype.api.domain.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<PersonEntity, Integer> {
    Optional<PersonEntity> getByPersonNumber(String personNumber);
}
