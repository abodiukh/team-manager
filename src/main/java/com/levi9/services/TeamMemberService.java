package com.levi9.services;

import com.levi9.components.Converter;
import com.levi9.domain.Employee;
import com.levi9.domain.Team;
import com.levi9.domain.TeamMember;
import com.levi9.dto.TeamMemberDTO;
import com.levi9.repositories.EmployeeRepository;
import com.levi9.repositories.PositionRepository;
import com.levi9.repositories.SeniorityRepository;
import com.levi9.repositories.TeamMemberRepository;
import com.levi9.repositories.TeamRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TeamMemberService {

    @Autowired
    private TeamMemberRepository teamMemberRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PositionRepository positionRepository;

    @Autowired
    private SeniorityRepository seniorityRepository;

    @Autowired
    private Converter converter;

    @PreAuthorize("isAuthenticated()")
    @Transactional(readOnly = true)
    public TeamMemberDTO getMember(final Long id) {
        TeamMember teamMember = teamMemberRepository.findOne(id);
        return converter.toTeamMemberDTO(teamMember);
    }

    @PreAuthorize("isAuthenticated()")
    @Transactional(readOnly = true)
    public TeamMemberDTO getMemberByName(final String name) {
        TeamMember teamMember = teamMemberRepository.findByEmployeeName(name);
        if (teamMember != null) {
            return converter.toTeamMemberDTO(teamMember);
        }
        return null;
    }

    @PreAuthorize("isAuthenticated()")
    public void addMember(final TeamMemberDTO teamMemberDTO) {
        TeamMember teamMember = new TeamMember();
        Employee employee = employeeRepository.findByName(teamMemberDTO.getName());
        if (employee == null) {
            employee = new Employee();
        }
        updateEmployee(teamMemberDTO, employee);
        teamMember.setEmployee(employee);
        teamMember.setInvolvement(teamMemberDTO.getInvolvement());
        teamMember.setVacancy(teamMemberDTO.getVacancy());
        Team team = teamRepository.findOne(teamMemberDTO.getTeamId());
        teamMember.setTeam(team);
        teamMemberRepository.save(teamMember);
    }

    @PreAuthorize("isAuthenticated()")
    public void saveMember(final TeamMemberDTO teamMemberDTO) {
        TeamMember teamMember = teamMemberRepository.findOne(teamMemberDTO.getId());
        updateEmployee(teamMemberDTO, teamMember.getEmployee());
        teamMember.setInvolvement(teamMemberDTO.getInvolvement());
        teamMember.setVacancy(teamMemberDTO.getVacancy());
        teamMemberRepository.save(teamMember);
    }

    @PreAuthorize("isAuthenticated()")
    public void deleteMember(final Long id) {
        teamMemberRepository.delete(id);
    }

    private void updateEmployee(final TeamMemberDTO teamMemberDTO, final Employee employee) {
        employee.setName(teamMemberDTO.getName());
        employee.setPosition(positionRepository.findByName(teamMemberDTO.getPosition()));
        employee.setSeniority(seniorityRepository.findByName(teamMemberDTO.getSeniority()));
        employeeRepository.save(employee);
    }

}
