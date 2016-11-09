package com.levi9.repositories;

import com.levi9.domain.TeamMember;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamMemberRepository extends JpaRepository<TeamMember, Long> {

    TeamMember findByEmployeeName(String name);
}
