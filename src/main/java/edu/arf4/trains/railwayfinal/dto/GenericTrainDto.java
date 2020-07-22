package edu.arf4.trains.railwayfinal.dto;

import java.util.Set;

public class GenericTrainDto {

    private String number;
    private String route;
    private Set<RoutePointDto> routePointDtoSet;
    private ScheduleDto schedule;

    public String getNumber() {
        return number;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public Set<RoutePointDto> getRoutePointDtoSet() {
        return routePointDtoSet;
    }

    public ScheduleDto getSchedule() {
        return schedule;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setRoutePointDtoSet(Set<RoutePointDto> routePointDtoSet) {
        this.routePointDtoSet = routePointDtoSet;
    }

    public void setSchedule(ScheduleDto schedule) {
        this.schedule = schedule;
    }
}
