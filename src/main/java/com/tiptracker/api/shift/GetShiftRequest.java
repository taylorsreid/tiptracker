package com.tiptracker.api.shift;

import lombok.Getter;
import java.sql.Date;

@Getter
public class GetShiftRequest {

    private Date startDate;
    private Date endDate;

}
