package com.tiptracker.api.shift;

import lombok.Getter;
import java.sql.Date;

@Getter
public class PostShiftRequest {

    private Date shiftDate;
    private String jobTitle;
    private Double hoursWorked;
    private Double hourlyRate;
    private Double tipsEarned;

}
