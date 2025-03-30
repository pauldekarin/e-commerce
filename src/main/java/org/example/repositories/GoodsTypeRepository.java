package org.example.repositories;

import org.example.entities.GoodsType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodsTypeRepository extends JpaRepository<GoodsType, Long> {
}
