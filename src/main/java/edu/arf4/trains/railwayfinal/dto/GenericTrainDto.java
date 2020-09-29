package edu.arf4.trains.railwayfinal.dto;

import java.util.List;
import java.util.Set;

public class GenericTrainDto {

    private String number;
    private String route;
    private List<RoutePointDto> routePointDtoList;
    private ScheduleDto schedule;

    private int numOfPlazkartCars;
    private int numOfSeatsInPlazkartCar;
    private int numOfCoopeCars;
    private int numOfSeatsInCoopeCar;
    private int numOfSwCars;
    private int numOfSeatsInSwcar;

    @Override
    public String toString() {
        return "GenericTrainDto{" +
                "number='" + number + '\'' +
                ", route='" + route + '\'' +
                ", routePointDtoSet=" + routePointDtoList +
                ", schedule=" + schedule +
                ", numOfPlazkartCars=" + numOfPlazkartCars +
                ", numOfSeatsInPlazkartCar=" + numOfSeatsInPlazkartCar +
                ", numOfCoopeCars=" + numOfCoopeCars +
                ", numOfSeatsInCoopeCar=" + numOfSeatsInCoopeCar +
                ", numOfSwCars=" + numOfSwCars +
                ", numOfSeatsInSwcar=" + numOfSeatsInSwcar +
                '}';
    }

    public String getNumber() {
        return number;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public ScheduleDto getSchedule() {
        return schedule;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public List<RoutePointDto> getRoutePointDtoList() {
        return routePointDtoList;
    }

    public void setRoutePointDtoList(List<RoutePointDto> routePointDtoList) {
        this.routePointDtoList = routePointDtoList;
    }

    public void setSchedule(ScheduleDto schedule) {
        this.schedule = schedule;
    }

    public int getNumOfPlazkartCars() {
        return numOfPlazkartCars;
    }

    public void setNumOfPlazkartCars(int numOfPlazkartCars) {
        this.numOfPlazkartCars = numOfPlazkartCars;
    }

    public int getNumOfSeatsInPlazkartCar() {
        return numOfSeatsInPlazkartCar;
    }

    public void setNumOfSeatsInPlazkartCar(int numOfSeatsInPlazkartCar) {
        this.numOfSeatsInPlazkartCar = numOfSeatsInPlazkartCar;
    }

    public int getNumOfCoopeCars() {
        return numOfCoopeCars;
    }

    public void setNumOfCoopeCars(int numOfCoopeCars) {
        this.numOfCoopeCars = numOfCoopeCars;
    }

    public int getNumOfSeatsInCoopeCar() {
        return numOfSeatsInCoopeCar;
    }

    public void setNumOfSeatsInCoopeCar(int numOfSeatsInCoopeCar) {
        this.numOfSeatsInCoopeCar = numOfSeatsInCoopeCar;
    }

    public int getNumOfSwCars() {
        return numOfSwCars;
    }

    public void setNumOfSwCars(int numOfSwCars) {
        this.numOfSwCars = numOfSwCars;
    }

    public int getNumOfSeatsInSwcar() {
        return numOfSeatsInSwcar;
    }

    public void setNumOfSeatsInSwcar(int numOfSeatsInSwcar) {
        this.numOfSeatsInSwcar = numOfSeatsInSwcar;
    }
}
