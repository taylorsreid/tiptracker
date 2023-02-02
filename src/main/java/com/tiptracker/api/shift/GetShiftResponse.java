package com.tiptracker.api.shift;

import java.util.List;

public class GetShiftResponse {
    private boolean success;
    private String message;
    private List<Shift> shifts;

    public GetShiftResponse(boolean success, String message, List<Shift> shifts){
        this.success = success;
        this.message = message;
        this.shifts = shifts;
    }
}
