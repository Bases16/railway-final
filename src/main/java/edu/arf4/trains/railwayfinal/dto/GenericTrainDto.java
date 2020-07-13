package edu.arf4.trains.railwayfinal.dto;

import java.util.List;

public class GenericTrainDto {

    private String number;

    private List<RoutePointDto> routePointDtoList;

    private ScheduleDto schedule;

    // schedule ,  points


    public String getNumber() {
        return number;
    }

    public List<RoutePointDto> getRoutePointDtoList() {
        return routePointDtoList;
    }

    public ScheduleDto getSchedule() {
        return schedule;
    }
}
