package com.levi9.repositories;

import com.levi9.domain.Seniority;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SeniorityRepository extends JpaRepository<Seniority, Long> {
    Seniority findByName(String seniority);
}
