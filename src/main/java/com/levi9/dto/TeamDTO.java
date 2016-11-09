package com.levi9.dto;

import java.util.List;

public class TeamDTO {

    private Long id;

    private String name;

    private List<TeamMemberDTO> members;

    private List<RateDTO> rates;

    private Integer cost;

    public TeamDTO() {
    }

    public TeamDTO(final Long id, final String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public List<TeamMemberDTO> getMembers() {
        return members;
    }

    public void setMembers(final List<TeamMemberDTO> members) {
        this.members = members;
    }

    public List<RateDTO> getRates() {
        return rates;
    }

    public void setRates(final List<RateDTO> rates) {
        this.rates = rates;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(final Integer cost) {
        this.cost = cost;
    }
}
