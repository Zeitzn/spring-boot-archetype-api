package com.archetype.api.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(schema = "loans", name = "business_loan_type")
public class BusinessLoanTypeEntity extends Auditable {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @Column(name = "active")
        private boolean active;
        @ManyToOne
        @JoinColumn(name = "loan_type_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_business_loan_type_loan_type_id"))
        private LoanTypeEntity loanRequestType;
        @ManyToOne
        @JoinColumn(name = "organization_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_business_loan_type_organization_id"))
        private OrganizationEntity organization;
}
