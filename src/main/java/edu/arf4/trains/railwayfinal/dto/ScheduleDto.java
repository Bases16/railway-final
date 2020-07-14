package edu.arf4.trains.railwayfinal.dto;

public class ScheduleDto {

    private int weekPeriodicity;

    private boolean monday;
    private boolean tuesday;
    private boolean wednesday;
    private boolean thursday;
    private boolean friday;
    private boolean saturday;
    private boolean sunday;


    public int getWeekPeriodicity() {
        return weekPeriodicity;
    }

    public boolean getMonday() {
        return monday;
    }

    public boolean getTuesday() {
        return tuesday;
    }

    public boolean getWednesday() {
        return wednesday;
    }

    public boolean getThursday() {
        return thursday;
    }

    public boolean getFriday() {
        return friday;
    }

    public boolean getSaturday() {
        return saturday;
    }

    public boolean getSunday() {
        return sunday;
    }

    public void setWeekPeriodicity(int weekPeriodicity) {
        this.weekPeriodicity = weekPeriodicity;
    }

    public void setMonday(boolean monday) {
        this.monday = monday;
    }

    public void setTuesday(boolean tuesday) {
        this.tuesday = tuesday;
    }

    public void setWednesday(boolean wednesday) {
        this.wednesday = wednesday;
    }

    public void setThursday(boolean thursday) {
        this.thursday = thursday;
    }

    public void setFriday(boolean friday) {
        this.friday = friday;
    }

    public void setSaturday(boolean saturday) {
        this.saturday = saturday;
    }

    public void setSunday(boolean sunday) {
        this.sunday = sunday;
    }
}