package com.levi9.controllers;

import java.util.List;

import javax.validation.Valid;

import com.levi9.dto.RateDTO;
import com.levi9.services.RateService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/rate")
public class RateController {

    @Autowired
    private RateService rateService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity getRates() {
        List<RateDTO> rates = rateService.getRates();
        return new ResponseEntity<>(rates, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity getRate(@PathVariable("id") Long id) {
        RateDTO rate = rateService.getRate(id);
        return new ResponseEntity<>(rate, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity addRate(@RequestBody @Valid RateDTO rateDTO) {
        rateService.addRate(rateDTO);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity saveRate(@RequestBody @Valid RateDTO rateDTO) {
        if (rateDTO.getId() == null) {
            rateService.addRate(rateDTO);
        } else {
            rateService.saveRate(rateDTO);
        }
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteRate(@PathVariable("id") Long id) {
        rateService.deleteRate(id);
        return new ResponseEntity(HttpStatus.OK);
    }

}
