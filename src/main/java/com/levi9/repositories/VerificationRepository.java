package com.levi9.repositories;


import com.levi9.domain.Verification;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationRepository extends JpaRepository<Verification, Integer> {

    Verification findByToken(String token);
}
