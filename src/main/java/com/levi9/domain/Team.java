package com.levi9.domain;

import java.util.Set;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.OrderBy;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
public class Team {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OrderBy
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "team")
    @Cascade(CascadeType.DELETE)
    private Set<Rate> rates;

    @OrderBy
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "team")
    @Cascade(CascadeType.DELETE)
    private Set<TeamMember> teamMembers;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private Integer cost;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Set<TeamMember> getTeamMembers() {
        return teamMembers;
    }

    public void setTeamMembers(final Set<TeamMember> teamMembers) {
        this.teamMembers = teamMembers;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(final Integer cost) {
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Set<Rate> getRates() {
        return rates;
    }

    public void setRates(final Set<Rate> rates) {
        this.rates = rates;
    }

    public User getUser() {
        return user;
    }

    public void setUser(final User user) {
        this.user = user;
    }
}
