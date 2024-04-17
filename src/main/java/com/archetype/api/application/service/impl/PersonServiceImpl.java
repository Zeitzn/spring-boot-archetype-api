package com.archetype.api.application.service.impl;

import com.archetype.api.domain.entity.PersonEntity;
import com.archetype.api.application.service.PersonService;
import com.archetype.api.infraestructure.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class PersonServiceImpl implements PersonService {
    private final PersonRepository repository;
    @Override
    public Optional<PersonEntity> getByPersonNumber(String personNumber) {
        return repository.getByPersonNumber(personNumber);
    }
}
