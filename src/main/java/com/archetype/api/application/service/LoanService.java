package com.archetype.api.application.service;

import com.archetype.api.application.dto.request.LoanRegisterRequest;
import com.archetype.api.domain.entity.LoanEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LoanService {
    Page<LoanEntity> findAll(String personNumber, Pageable page);
    LoanEntity register(LoanRegisterRequest request);
}
