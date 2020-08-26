package edu.arf4.trains.railwayfinal.util;

import edu.arf4.trains.railwayfinal.model.Schedule;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Converter {


    private static final DateTimeFormatter DT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd' at 'HH:mm");
    private static final DateTimeFormatter T_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");


    public static String convertLocalDateTimeToString(LocalDateTime dateTime) {
        return dateTime == null ? null : dateTime.format(DT_FORMATTER);
    }

    public static String convertLocalTimeToString(LocalTime time) {
        return time == null ? null : time.format(T_FORMATTER);
    }

    public static LocalDateTime convertStringToLocalDateTime(String dateTime) {
        return dateTime == null ? null : LocalDateTime.parse(dateTime, DT_FORMATTER);
    }

    public static LocalTime convertStringToLocalTime(String time) {
        return time == null ? null : LocalTime.parse(time, T_FORMATTER);
    }

    public static List<DayOfWeek> getDaysOfWeekFromSchedule(Schedule schedule) {
        List<DayOfWeek> resultList = new ArrayList<>();
        if(schedule.getMonday()) resultList.add(DayOfWeek.MONDAY);
        if(schedule.getTuesday()) resultList.add(DayOfWeek.TUESDAY);
        if(schedule.getWednesday()) resultList.add(DayOfWeek.WEDNESDAY);
        if(schedule.getThursday()) resultList.add(DayOfWeek.THURSDAY);
        if(schedule.getFriday()) resultList.add(DayOfWeek.FRIDAY);
        if(schedule.getSaturday()) resultList.add(DayOfWeek.SATURDAY);
        if(schedule.getSunday()) resultList.add(DayOfWeek.SUNDAY);
        return resultList;
    }


}
