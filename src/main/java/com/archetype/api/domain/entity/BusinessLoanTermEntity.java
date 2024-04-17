package com.archetype.api.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(schema = "loans", name = "business_loan_term")
public class BusinessLoanTermEntity extends Auditable {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @Column(name = "business_unit_id", length = 30)
        private String businessUnitId;
        @Column(name = "description", columnDefinition = "TEXT")
        private String description;
        @Column(name = "active")
        private boolean active;
        @ManyToOne
        @JoinColumn(name = "organization_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_business_loan_term_organization_id"))
        private OrganizationEntity organization;
}
