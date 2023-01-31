package com.tiptracker.api.job;

import com.tiptracker.api.user.User;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;

@Entity
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private BigInteger id;

    private String jobTitle;
    private BigDecimal payRate;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public BigDecimal getPayRate() {
        return payRate;
    }

    public void setPayRate(BigDecimal payRate) {
        this.payRate = payRate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
