package com.levi9.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class RateItem {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seniority_id")
    private Seniority seniority;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prog_language_id")
    private ProgLanguage progLanguage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rate_id")
    private Rate rate;

    private Integer amount;

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

    public ProgLanguage getProgLanguage() {
        return progLanguage;
    }

    public void setProgLanguage(final ProgLanguage progLanguage) {
        this.progLanguage = progLanguage;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(final Integer amount) {
        this.amount = amount;
    }

    public Rate getRate() {
        return rate;
    }

    public void setRate(final Rate rate) {
        this.rate = rate;
    }
}
