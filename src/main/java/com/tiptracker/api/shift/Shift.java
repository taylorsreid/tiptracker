package com.tiptracker.api.shift;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tiptracker.api.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import java.math.BigInteger;
import java.sql.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Shift {

    public Shift(User user, PostShiftRequest postShiftRequest){
        this.user = user;
        this.jobTitle = postShiftRequest.getJobTitle();
        this.shiftDate = postShiftRequest.getShiftDate();
        this.hoursWorked = postShiftRequest.getHoursWorked();
        this.hourlyRate = postShiftRequest.getHourlyRate();
        this.tipsEarned = postShiftRequest.getTipsEarned();

    }

    @JsonIgnore
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private BigInteger shiftId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    @Autowired
    private User user;

    private String jobTitle;
    private Date shiftDate;
    private Double hoursWorked;
    private Double hourlyRate;
    private Double tipsEarned;

}
