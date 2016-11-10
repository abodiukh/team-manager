package com.levi9.controllers;

import java.util.List;

import com.levi9.domain.Employee;
import com.levi9.domain.Position;
import com.levi9.domain.Seniority;
import com.levi9.services.PropertiesService;

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
    private PropertiesService propertiesService;

    @RequestMapping(value = "/positions", method = RequestMethod.GET)
    public ResponseEntity getPositions() {
        List<Position> positions = propertiesService.getPositions();
        return new ResponseEntity<>(positions, HttpStatus.OK);
    }

    @RequestMapping(value = "/seniorities", method = RequestMethod.GET)
    public ResponseEntity getSeniorities() {
        List<Seniority> seniorities = propertiesService.getSeniorities();
        return new ResponseEntity<>(seniorities, HttpStatus.OK);
    }

    @RequestMapping(value = "/employees", method = RequestMethod.GET)
    public ResponseEntity getEmployees() {
        List<Employee> employees = propertiesService.getEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

}
