package com.archetype.api.application.service;

import com.archetype.api.domain.entity.PersonEntity;

import java.util.Optional;

public interface PersonService {
    Optional<PersonEntity> getByPersonNumber(String personNumber);
}
