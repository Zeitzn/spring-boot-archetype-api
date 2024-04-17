package com.archetype.api.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoanRegisterRequest {
    @NotBlank(message = "personNumber no enviado")
    @NotNull(message = "personNumber no enviado")
    @NotEmpty(message = "personNumber no enviado")
    private String personNumber;
}
