package edu.arf4.trains.railwayfinal.model;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class Schedule {

    @NotNull
    private Integer weekPeriodicity;

    @NotNull
    private Boolean monday;
    @NotNull
    private Boolean tuesday;
    @NotNull
    private Boolean wednesday;
    @NotNull
    private Boolean thursday;
    @NotNull
    private Boolean friday;
    @NotNull
    private Boolean saturday;
    @NotNull
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
