package com.levi9.dto;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class RateDTO {

    private Long id;

    @NotNull
    private Long teamId;

    @NotNull
    @NotEmpty
    private String position;

    @NotNull
    @NotEmpty
    private String seniority;

    @NotNull
    private Integer rate;

    public RateDTO() {
    }

    public RateDTO(final Long id, final String position, final String seniority, final Integer rate) {
        this.id = id;
        this.position = position;
        this.seniority = seniority;
        this.rate = rate;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(final String position) {
        this.position = position;
    }

    public String getSeniority() {
        return seniority;
    }

    public void setSeniority(final String seniority) {
        this.seniority = seniority;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(final Integer rate) {
        this.rate = rate;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(final Long teamId) {
        this.teamId = teamId;
    }
}
