package com.archetype.api.infraestructure.repository;

import com.archetype.api.domain.entity.OrganizationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationRepository extends JpaRepository<OrganizationEntity, Integer> {
}
