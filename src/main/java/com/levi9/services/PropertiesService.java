package com.levi9.services;

import java.util.List;

import com.levi9.domain.Employee;
import com.levi9.domain.Position;
import com.levi9.domain.Seniority;
import com.levi9.repositories.EmployeeRepository;
import com.levi9.repositories.PositionRepository;
import com.levi9.repositories.SeniorityRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class PropertiesService {

    @Autowired
    private PositionRepository positionRepository;

    @Autowired
    private SeniorityRepository seniorityRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Position> getPositions() {
        return positionRepository.findAll();
    }

    public List<Seniority> getSeniorities() {
        return seniorityRepository.findAll();
    }

    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }
}
