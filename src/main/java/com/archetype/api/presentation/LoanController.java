package com.archetype.api.presentation;

import com.archetype.api.application.dto.ResponseDto;
import com.archetype.api.application.dto.request.LoanRegisterRequest;
import com.archetype.api.application.dto.response.LoanResponse;
import com.archetype.api.application.mapper.LoanMapper;
import com.archetype.api.application.service.LoanService;
import com.archetype.api.domain.entity.LoanEntity;
import com.archetype.api.exception.ServerErrorException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.OPTIONS })
@RestController
@RequestMapping(path = "/api/archetype/v1/loans")
@RequiredArgsConstructor
public class LoanController {
    private final LoanService service;
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ResponseDto<Page<LoanResponse>>> findAll(@RequestParam(required = false) Integer count, @RequestParam String personNumber, Pageable pageable) {
        try {
            Page<LoanEntity> result = service.findAll(personNumber, pageable);
            return ResponseEntity.ok(ResponseDto.<Page<LoanResponse>>builder()
                    .code("P-001")
                    .timestamp(new Date())
                    .message("Operación exitosa")
                    .content(result.map(LoanMapper::toDto))
                    .build());
        } catch (ServerErrorException e) {
            throw ServerErrorException.builder().code(e.getCode()).message(e.getMessage()).build();
        }
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ResponseDto<LoanResponse>> register(@Valid @RequestBody LoanRegisterRequest request) {
        try {
            LoanEntity result = service.register(request);
            return ResponseEntity.ok(ResponseDto.<LoanResponse>builder()
                    .code("P-001")
                    .timestamp(new Date())
                    .message("Operación exitosa")
                    .content(LoanMapper.toDto(result))
                    .build());
        } catch (ServerErrorException e) {
            throw ServerErrorException.builder().code(e.getCode()).message(e.getMessage()).build();
        }
    }
}
