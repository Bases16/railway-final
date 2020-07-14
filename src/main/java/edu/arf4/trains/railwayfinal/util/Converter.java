package edu.arf4.trains.railwayfinal.util;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Converter {


    private static final DateTimeFormatter DT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd' at 'HH:mm");
    private static final DateTimeFormatter T_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");


    public static String convertLocalDateTimeToString(LocalDateTime dateTime) {
        return dateTime.format(DT_FORMATTER);
    }

    public static String convertLocalTimeToString(LocalTime time) {
        return time.format(T_FORMATTER);
    }


    public static LocalDateTime convertStringToLocalDateTime(String dateTime) {
        return LocalDateTime.parse(dateTime, DT_FORMATTER);
    }

    public static LocalTime convertStringToLocalTime(String time) {
        return LocalTime.parse(time, T_FORMATTER);
    }


}
