package com.archetype.api.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(schema = "loans", name = "business_loan_requirement")
public class BusinessLoanReqEntity extends Auditable {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @Column(name = "active")
        private boolean active;
        @ManyToOne
        @JoinColumn(name = "business_loan_type_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_loan_business_loan_type_id"))
        private BusinessLoanTypeEntity businessLoanRequestType;
}
