package com.archetype.api.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(schema = "loans", name = "loan")
public class LoanEntity extends Auditable {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        /**
         * Nombres y apellidos del colaborador
         */
        @Column(name = "employee_name", length = 200)
        private String employeeName;
        /**
         * Número de identificación del colaborador
         */
        @Column(name = "employee_document_number", length = 12)
        private String employeeDocumentNumber;
        /**
         * Cargo/puesto del colaborador
         */
        @Column(name = "employee_position", length = 200)
        private String employeePosition;
        /**
         * Código Ofiplan del colaborador
         */
        @Column(name = "employee_ofiplan_code", length = 5)
        private String employeeOfiplanCode;
        /**
         * Código de empresa (HCM)
         */
        @Column(name = "business_unit_id", length = 30)
        private String businessUnitId;
        /**
         * Nombre de empresa (HCM)
         */
        @Column(name = "business_name", length = 200)
        private String businessName;
        /**
         * Fecha de ingreso a la empresa
         */
        @Column(name = "admission_date ")
        private Date admissionDate;
        /**
         * Importe solicitado
         */
        @Column(name = "request_amount", columnDefinition = "DECIMAL(10, 2)")
        private BigDecimal requestAmount;
        /**
         * Cuotas a pagar/Cuotas solicitadas
         */
        @Column(name = "total_quote")
        private Integer totalQuote;
        /**
         * Sueldo básico
         */
        @Column(name = "basic_salary", columnDefinition = "DECIMAL(10, 2)")
        private BigDecimal basicSalary;
        /**
         * Tasa de interés
         */
        @Column(name = "interest_rate")
        private Double interestRate;
        /**
         * Estado de solicitud superapp (En Proceso, Procesada, Rechazada)
         */
        @Column(name = "superapp_status", length = 20)
        private String superAppStatus;
        /**
         * Estado de solicitud backoffice (Pendiente, Procesada, Rechazada)
         */
        @Column(name = "backoffice_status", length = 20)
        private String backofficeStatus;
        /**
         * Fecha de solicitud
         */
        @Column(name = "request_date ")
        private Date requestDate;
        /**
         * Fecha de atención
         */
        @Column(name = "attention_date ")
        private Date attentionDate;
        /**
         * Motivo de rechazo
         */
        @Column(name = "reject_reason", columnDefinition = "TEXT")
        private String rejectReason;

        /**
         * Tipo de préstamo habilitado para la empresa
         */
        @ManyToOne
        @JoinColumn(name = "business_loan_type_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_loan_business_loan_type_id"))
        private BusinessLoanTypeEntity loanType;
}
