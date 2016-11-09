package com.levi9.controllers;

import javax.validation.Valid;

import com.levi9.dto.TeamMemberDTO;
import com.levi9.services.TeamMemberService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/member")
public class TeamMemberController {

    @Autowired
    private TeamMemberService teamMemberService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity getMember(@PathVariable("id") Long id) {
        TeamMemberDTO teamMemberDTO = teamMemberService.getMember(id);
        return new ResponseEntity<>(teamMemberDTO, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity addMember(@RequestBody @Valid TeamMemberDTO teamMemberDTO) {
        TeamMemberDTO existMember = teamMemberService.getMemberByName(teamMemberDTO.getName());
        if (existMember == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        teamMemberService.addMember(teamMemberDTO);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity saveMember(@RequestBody @Valid TeamMemberDTO teamMemberDTO) {
        if (teamMemberDTO.getId() == null) {
            teamMemberService.addMember(teamMemberDTO);
        } else {
            teamMemberService.saveMember(teamMemberDTO);
        }
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteMember(@PathVariable("id") Long id) {
        teamMemberService.deleteMember(id);
        return new ResponseEntity(HttpStatus.OK);
    }

}
