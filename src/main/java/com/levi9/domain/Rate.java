package com.levi9.domain;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Rate {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "rate")
    private Set<Team> teams;

    @OneToMany(mappedBy = "rate")
    private Set<RateItem> rateItems;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Set<Team> getTeams() {
        return teams;
    }

    public void setTeams(final Set<Team> teams) {
        this.teams = teams;
    }

    public Set<RateItem> getRateItems() {
        return rateItems;
    }

    public void setRateItems(final Set<RateItem> rateItems) {
        this.rateItems = rateItems;
    }
}
