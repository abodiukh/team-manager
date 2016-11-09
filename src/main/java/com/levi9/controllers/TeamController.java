package com.levi9.controllers;

import java.security.Principal;
import java.util.List;

import com.levi9.dto.TeamDTO;
import com.levi9.services.TeamService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/team")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity getTeams() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            UserDetails details = (UserDetails) authentication.getPrincipal();
            List<TeamDTO> result = teamService.getTeams(details.getUsername());
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.UNAUTHORIZED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity getTeam(@PathVariable("id") Long id) {
        TeamDTO teamDTO = teamService.getTeam(id);
        return new ResponseEntity<>(teamDTO, HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity addTeam(@RequestBody TeamDTO teamDTO, Principal principal) {
        teamService.addTeam(principal.getName(), teamDTO);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity saveTeam(@RequestBody TeamDTO teamDTO) {
        teamService.saveTeam(teamDTO);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteTeam(@PathVariable("id") Long id) {
        teamService.deleteTeam(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
