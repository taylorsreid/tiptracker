package com.tiptracker.api.shift;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigInteger;
import java.sql.Date;

@Entity
public class Shift {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private BigInteger shiftId;

    private String uuid;
    private Date shiftDate;
    private Double hoursWorked;
    private Double hourlyRate;
    private Double tipsEarned;

    public BigInteger getShiftId() {
        return shiftId;
    }

    public void setShiftId(BigInteger shiftId) {
        this.shiftId = shiftId;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Date getShiftDate() {
        return shiftDate;
    }

    public void setShiftDate(Date shiftDate) {
        this.shiftDate = shiftDate;
    }

    public Double getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(Double hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    public Double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(Double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public Double getTipsEarned() {
        return tipsEarned;
    }

    public void setTipsEarned(Double tipsEarned) {
        this.tipsEarned = tipsEarned;
    }
}
