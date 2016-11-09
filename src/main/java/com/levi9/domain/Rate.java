package com.levi9.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Rate {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seniority_id")
    private Seniority seniority;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "position")
    private Position position;

    private Integer rate;

    private Boolean isDefault;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Seniority getSeniority() {
        return seniority;
    }

    public void setSeniority(final Seniority seniority) {
        this.seniority = seniority;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(final Position position) {
        this.position = position;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(final Integer rate) {
        this.rate = rate;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(final Team team) {
        this.team = team;
    }

    public Boolean getDefault() {
        return isDefault;
    }

    public void setDefault(final Boolean aDefault) {
        isDefault = aDefault;
    }
}
