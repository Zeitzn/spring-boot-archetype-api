package com.archetype.api.application.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class LoanResponse {
    private Long id;
    private String employeeName;
    private String employeeDocumentNumber;
    private String admissionDate;
}
