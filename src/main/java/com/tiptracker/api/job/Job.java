package com.tiptracker.api.job;

import com.tiptracker.api.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.BigInteger;

@Entity
@Getter
@Setter
public class Job {

    // primary key
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private BigInteger jobId;

    // foreign key
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    private String jobTitle;
    private BigDecimal payRate;

}
