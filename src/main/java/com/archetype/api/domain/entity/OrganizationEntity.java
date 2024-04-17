package com.archetype.api.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="organization", schema = "seguridad")
public class OrganizationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="legal_entity_code", length = 2)
    private String legalEntityCode;
    @Column(name="business_unitid", length = 80)
    private String businessUnitId;
    @Column(name="social_reason", length = 250)
    private String socialReason;

    @Column(name="short_name", length = 250)
    private String shortName;
}