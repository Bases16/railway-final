package edu.arf4.trains.railwayfinal.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Schedule {

    @Column(nullable = false)
    private Integer weekPeriodicity;

    @Column(nullable = false)
    private Boolean monday;
    @Column(nullable = false)
    private Boolean tuesday;
    @Column(nullable = false)
    private Boolean wednesday;
    @Column(nullable = false)
    private Boolean thursday;
    @Column(nullable = false)
    private Boolean friday;
    @Column(nullable = false)
    private Boolean saturday;
    @Column(nullable = false)
    private Boolean sunday;


    public Integer getWeekPeriodicity() {
        return weekPeriodicity;
    }

    public void setWeekPeriodicity(Integer weekPeriodicity) {
        this.weekPeriodicity = weekPeriodicity;
    }

    public Boolean getMonday() {
        return monday;
    }

    public void setMonday(Boolean monday) {
        this.monday = monday;
    }

    public Boolean getTuesday() {
        return tuesday;
    }

    public void setTuesday(Boolean tuesday) {
        this.tuesday = tuesday;
    }

    public Boolean getWednesday() {
        return wednesday;
    }

    public void setWednesday(Boolean wednesday) {
        this.wednesday = wednesday;
    }

    public Boolean getThursday() {
        return thursday;
    }

    public void setThursday(Boolean thursday) {
        this.thursday = thursday;
    }

    public Boolean getFriday() {
        return friday;
    }

    public void setFriday(Boolean friday) {
        this.friday = friday;
    }

    public Boolean getSaturday() {
        return saturday;
    }

    public void setSaturday(Boolean saturday) {
        this.saturday = saturday;
    }

    public Boolean getSunday() {
        return sunday;
    }

    public void setSunday(Boolean sunday) {
        this.sunday = sunday;
    }
}
