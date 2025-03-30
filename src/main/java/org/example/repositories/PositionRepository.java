package org.example.repositories;

import org.example.entities.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionRepository extends JpaRepository<Position, Long> {
    @Query("SELECT COUNT(p) > 0 FROM Position p WHERE p.name =:name")
    boolean existsByName(@Param("name") String name);
}
