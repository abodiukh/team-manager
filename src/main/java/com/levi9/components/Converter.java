package com.levi9.components;

import java.util.List;
import java.util.stream.Collectors;

import com.levi9.domain.Employee;
import com.levi9.domain.Position;
import com.levi9.domain.Rate;
import com.levi9.domain.Seniority;
import com.levi9.domain.Team;
import com.levi9.domain.TeamMember;
import com.levi9.dto.RateDTO;
import com.levi9.dto.TeamDTO;
import com.levi9.dto.TeamMemberDTO;

import org.springframework.stereotype.Component;

@Component
public final class Converter {

    private List<RateDTO> getTeamRates(final Team team) {
        return team.getRates().stream().map(this::toRateDTO).collect(Collectors.toList());
    }

    private List<TeamMemberDTO> getTeamMembers(Team team) {
        return team.getTeamMembers().stream().map(this::toTeamMemberDTO).collect(Collectors.toList());
    }

    public TeamDTO toTeamDTO(final Team team) {
        TeamDTO teamDTO = new TeamDTO(team.getId(), team.getName());
        teamDTO.setMembers(getTeamMembers(team));
        teamDTO.setRates(getTeamRates(team));
        teamDTO.setCost(team.getCost());
        return teamDTO;
    }

    public RateDTO toRateDTO(final Rate rate) {
        Position position = rate.getPosition();
        Seniority seniority = rate.getSeniority();
        RateDTO rateDTO = new RateDTO(rate.getId(), position != null ? position.getName() : "",
                seniority != null ? seniority.getName() : "", rate.getRate());
        rateDTO.setTeamId(rate.getTeam().getId());
        return rateDTO;
    }

    public TeamMemberDTO toTeamMemberDTO(final TeamMember teamMember) {
        Employee employee = teamMember.getEmployee();
        Position position = employee.getPosition();
        Seniority seniority = employee.getSeniority();
        TeamMemberDTO teamMemberDTO = new TeamMemberDTO(teamMember.getId(), teamMember.getTeam().getId(), employee.getName());
        teamMemberDTO.setPosition(position != null ? position.getName() : "");
        teamMemberDTO.setSeniority(seniority != null ? seniority.getName() : "");
        teamMemberDTO.setInvolvement(teamMember.getInvolvement() != null ? teamMember.getInvolvement() : 100);
        teamMemberDTO.setVacancy(teamMember.isVacancy());
        return teamMemberDTO;
    }
}
