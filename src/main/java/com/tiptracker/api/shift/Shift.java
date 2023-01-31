package com.tiptracker.api.shift;

import com.tiptracker.api.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.sql.Date;

@Entity
@Getter
@Setter
public class Shift {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private BigInteger shiftId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    private Date shiftDate;
    private Double hoursWorked;
    private Double hourlyRate;
    private Double tipsEarned;

}
