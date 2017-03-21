package com.levi9.domain;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class Seniority {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    public Seniority() {
    }

    public Seniority(final String name) {
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
}
