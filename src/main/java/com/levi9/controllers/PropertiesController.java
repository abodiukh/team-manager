package com.levi9.controllers;

import java.util.List;

import com.levi9.domain.Position;
import com.levi9.domain.Seniority;
import com.levi9.repositories.PositionRepository;
import com.levi9.repositories.SeniorityRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/properties")
public class PropertiesController {

    @Autowired
    private PositionRepository positionRepository;

    @Autowired
    private SeniorityRepository seniorityRepository;

    @RequestMapping(value = "/positions", method = RequestMethod.GET)
    public ResponseEntity getPositions(){
        List<Position> positions = positionRepository.findAll();
        return new ResponseEntity<>(positions, HttpStatus.OK);
    }

    @RequestMapping(value = "/seniorities", method = RequestMethod.GET)
    public ResponseEntity getSeniorities(){
        List<Seniority> seniorities = seniorityRepository.findAll();
        return new ResponseEntity<>(seniorities, HttpStatus.OK);
    }

}
