package com.tiptracker.api.shift;

import lombok.Getter;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
public class PostShiftRequest {

    private LocalDate shiftDate;
    private String jobTitle;
    private BigDecimal hoursWorked;
    private BigDecimal hourlyRate;
    private BigDecimal tipsEarned;

}
