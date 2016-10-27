package com.levi9.domain;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Developer {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private ProgLanguage progLanguage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seniority_id")
    private Seniority seniority;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "team_developer",
            joinColumns = {@JoinColumn(name = "developer_id")},
            inverseJoinColumns = {@JoinColumn(name = "team_id")})
    private Set<Team> teams;

    private String name;

    private Integer involvement;

    private boolean vacancy;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public ProgLanguage getProgLanguage() {
        return progLanguage;
    }

    public void setProgLanguage(final ProgLanguage progLanguage) {
        this.progLanguage = progLanguage;
    }

    public Seniority getSeniority() {
        return seniority;
    }

    public void setSeniority(final Seniority seniority) {
        this.seniority = seniority;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Integer getInvolvement() {
        return involvement;
    }

    public void setInvolvement(final Integer involvement) {
        this.involvement = involvement;
    }

    public boolean isVacancy() {
        return vacancy;
    }

    public void setVacancy(final boolean vacancy) {
        this.vacancy = vacancy;
    }

    public Set<Team> getTeams() {
        return teams;
    }

    public void setTeams(final Set<Team> teams) {
        this.teams = teams;
    }
}
