package com.levi9.services;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.levi9.components.Converter;
import com.levi9.components.TeamCalculator;
import com.levi9.domain.Team;
import com.levi9.domain.User;
import com.levi9.dto.TeamDTO;
import com.levi9.repositories.TeamRepository;
import com.levi9.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TeamService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private Converter converter;

    @Autowired
    private TeamCalculator teamCalculator;

    @PreAuthorize("isAuthenticated()")
    @Transactional(readOnly = true)
    public List<TeamDTO> getTeams(String userName) {
        User user = userRepository.findByName(userName);
        if (user != null) {
            return user.getTeams().stream().map(converter::toTeamDTO).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    @PreAuthorize("isAuthenticated()")
    public TeamDTO getTeam(final Long id) {
        Team team = teamRepository.findOne(id);
        team.setCost(teamCalculator.getCost(team));
        teamRepository.save(team);
        return converter.toTeamDTO(team);
    }

    @PreAuthorize("isAuthenticated()")
    public void addTeam(final String userName, final TeamDTO teamDTO) {
        User user = userRepository.findByName(userName);
        Team team = new Team();
        team.setName(teamDTO.getName());
        team.setUser(user);
        teamRepository.save(team);
    }

    @PreAuthorize("isAuthenticated()")
    public void saveTeam(TeamDTO teamDTO) {
        Team team = teamRepository.findOne(teamDTO.getId());
        team.setName(teamDTO.getName());
        teamRepository.save(team);
    }

    @PreAuthorize("isAuthenticated()")
    public void deleteTeam(final Long id) {
        teamRepository.delete(id);
    }

}
