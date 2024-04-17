package com.archetype.api.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(schema = "loans", name = "loan_type")
public class LoanTypeEntity extends Auditable {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;
        @Column(name = "name", length = 20)
        private String name;
        @Column(name = "description", columnDefinition = "TEXT")
        private String description;
        @Column(name = "ofiplan_code", length = 5)
        private String ofiplanCode;
}
