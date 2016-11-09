package com.levi9.repositories;

import com.levi9.domain.Position;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository extends JpaRepository<Position, Long> {
    Position findByName(String position);
}
