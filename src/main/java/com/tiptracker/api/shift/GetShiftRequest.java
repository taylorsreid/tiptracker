package com.tiptracker.api.shift;

import lombok.Getter;
import java.time.LocalDate;

@Getter
public class GetShiftRequest {

    private LocalDate startDate;
    private LocalDate endDate;

}
