package com.levi9.services;

import java.util.List;
import java.util.stream.Collectors;

import com.levi9.components.Converter;
import com.levi9.domain.Rate;
import com.levi9.dto.RateDTO;
import com.levi9.repositories.PositionRepository;
import com.levi9.repositories.RateRepository;
import com.levi9.repositories.SeniorityRepository;
import com.levi9.repositories.TeamRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class RateService {

    @Autowired
    private RateRepository rateRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private PositionRepository positionRepository;

    @Autowired
    private SeniorityRepository seniorityRepository;

    @Autowired
    private Converter converter;

    @PreAuthorize("isAuthenticated()")
    public List<RateDTO> getRates() {
        List<Rate> rates = rateRepository.findAll();
        return rates.stream().map(rate -> converter.toRateDTO(rate)).collect(Collectors.toList());
    }

    @PreAuthorize("isAuthenticated()")
    public RateDTO getRate(final Long id) {
        Rate rate = rateRepository.findOne(id);
        return converter.toRateDTO(rate);
    }

    @PreAuthorize("isAuthenticated()")
    public void addRate(final RateDTO rateDTO) {
        Rate rate = new Rate();
        rate.setTeam(teamRepository.findOne(rateDTO.getTeamId()));
        rate.setPosition(positionRepository.findByName(rateDTO.getPosition()));
        rate.setSeniority(seniorityRepository.findByName(rateDTO.getSeniority()));
        rate.setRate(rateDTO.getRate());
        rateRepository.save(rate);
    }

    @PreAuthorize("isAuthenticated()")
    public void saveRate(final RateDTO rateDTO) {
        Rate rate = rateRepository.findOne(rateDTO.getId());
        rate.setTeam(teamRepository.findOne(rateDTO.getTeamId()));
        rate.setPosition(positionRepository.findByName(rateDTO.getPosition()));
        rate.setSeniority(seniorityRepository.findByName(rateDTO.getSeniority()));
        rate.setRate(rateDTO.getRate());
        rateRepository.save(rate);
    }

    @PreAuthorize("isAuthenticated()")
    public void deleteRate(final Long id) {
        rateRepository.delete(id);
    }
}
