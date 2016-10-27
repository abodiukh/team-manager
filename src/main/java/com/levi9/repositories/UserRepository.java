package com.levi9.repositories;

import com.levi9.domain.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByName(String username);

    User findByEmail(String email);
}
