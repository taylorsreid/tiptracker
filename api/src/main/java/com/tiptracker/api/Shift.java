package com.tiptracker.api;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Shift {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer shiftId;

    private String uuid;
    private String date;
    private Double hoursWorked;
    private Double hourlyRate;
    private Double tipsEarned;

    public Integer getShiftId() {
        return shiftId;
    }

    public String getUuid() {
        return uuid;
    }

    public String getDate() {
        return date;
    }

    public Double getHoursWorked() {
        return hoursWorked;
    }

    public Double getHourlyRate() {
        return hourlyRate;
    }

    public Double getTipsEarned() {
        return tipsEarned;
    }

    public void setShiftId(Integer shiftId) {
        this.shiftId = shiftId;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setHoursWorked(Double hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    public void setHourlyRate(Double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public void setTipsEarned(Double tipsEarned) {
        this.tipsEarned = tipsEarned;
    }
}
