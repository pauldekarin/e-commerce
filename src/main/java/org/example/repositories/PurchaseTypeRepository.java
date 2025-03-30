package org.example.repositories;

import org.example.dto.PurchaseTypeDTO;
import org.example.entities.PurchaseType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseTypeRepository extends JpaRepository<PurchaseType, Long> {
}
