package org.example.repositories;

import org.example.entities.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {
    @Query("SELECT COUNT(s) > 0 FROM Shop s WHERE s.name = :name")
    boolean existsByName(@Param("name") String name);
}
