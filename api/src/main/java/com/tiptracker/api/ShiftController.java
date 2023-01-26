package com.tiptracker.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/shift")
public class ShiftController {

    @Autowired
    private ShiftRepository shiftRepository;

    @PostMapping // Map ONLY POST Requests
    public @ResponseBody String addNewShift (
            @RequestParam String uuid,
            @RequestParam String date,
            @RequestParam Double hoursWorked,
            @RequestParam Double hourlyRate,
            @RequestParam Double tipsEarned)
    {
        Shift shift = new Shift();
        shift.setUuid(uuid);
        shift.setDate(date);
        shift.setHoursWorked(hoursWorked);
        shift.setHourlyRate(hourlyRate);
        shift.setTipsEarned(tipsEarned);
        shiftRepository.save(shift);
        return "Saved";
    }

}