package com.levi9.dto;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class TeamMemberDTO {

    private Long id;

    private Long teamId;

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @NotEmpty
    private String seniority;

    @NotNull
    @NotEmpty
    private String position;

    private Integer involvement;

    private Boolean vacancy;

    public TeamMemberDTO() {
    }

    public TeamMemberDTO(final Long id, final Long teamId, final String name) {
        this.id = id;
        this.teamId = teamId;
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

    public String getSeniority() {
        return seniority;
    }

    public void setSeniority(final String seniority) {
        this.seniority = seniority;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(final String position) {
        this.position = position;
    }

    public Integer getInvolvement() {
        return involvement;
    }

    public void setInvolvement(final Integer involvement) {
        this.involvement = involvement;
    }

    public Boolean getVacancy() {
        return vacancy;
    }

    public void setVacancy(final Boolean vacancy) {
        this.vacancy = vacancy;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(final Long teamId) {
        this.teamId = teamId;
    }
}
