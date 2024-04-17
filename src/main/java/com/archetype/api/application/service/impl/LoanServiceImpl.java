package com.archetype.api.application.service.impl;

import com.archetype.api.application.dto.request.LoanRegisterRequest;
import com.archetype.api.domain.entity.LoanEntity;
import com.archetype.api.domain.entity.PersonEntity;
import com.archetype.api.exception.NotFoundException;
import com.archetype.api.application.service.LoanService;
import com.archetype.api.application.service.PersonService;
import com.archetype.api.infraestructure.repository.LoanRepository;
import com.archetype.api.infraestructure.rest.HcmRestClient;
import com.archetype.api.infraestructure.rest.response.HcmWorkerDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service
public class LoanServiceImpl implements LoanService {
    private final LoanRepository repository;
    private final PersonService personService;
    private final HcmRestClient hcmRestClient;
    @Override
    public Page<LoanEntity> findAll(String personNumber, Pageable page) {
        HcmWorkerDto worker = hcmRestClient.getWorkerByPersonNumber(personNumber);
        log.info("Worker: {}", worker);
        return repository.findAll(page);
    }

    @Override
    public LoanEntity register(LoanRegisterRequest request) {

            // Obtener datos de la persona en HCM
            HcmWorkerDto worker = hcmRestClient.getWorkerByPersonNumber(request.getPersonNumber());
            log.info("HCM Worker => {}", worker.toString());

            // Obtener datos de la persona en Meys
            Optional<PersonEntity> existingPerson = personService.getByPersonNumber(request.getPersonNumber());
            if(existingPerson.isEmpty()) {
                throw NotFoundException.builder().code("40402").message(String.format("(Meys) No existe la persona de personNumber %s", request.getPersonNumber())).build();
            }

            return LoanEntity.builder().build();
    }
}
