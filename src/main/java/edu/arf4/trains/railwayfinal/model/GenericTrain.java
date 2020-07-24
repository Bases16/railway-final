package edu.arf4.trains.railwayfinal.model;

import edu.arf4.trains.railwayfinal.util.Constants;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@org.hibernate.annotations.BatchSize(size = 9)
public class GenericTrain {

    @Id
    @GeneratedValue(generator = Constants.MY_ID_GENERATOR)
    private Long id;

    @Column(nullable = false, unique = true)
    private String number;

    @Column(nullable = false)
    private String route;

    private Integer numOfPlazkartCars;
    private Integer numOfSeatsInPlazkartCar;
    private Integer numOfCoopeCars;
    private Integer numOfSeatsInCoopeCar;
    private Integer numOfSwCars;
    private Integer numOfSeatsInSwCar;

    @Embedded
    private Schedule schedule;

    @Size(min = 2)
    @org.hibernate.annotations.Fetch(org.hibernate.annotations.FetchMode.SUBSELECT)
    @OneToMany(mappedBy = "genericTrain", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, orphanRemoval = false)
    private Set<RoutePoint> routePoints = new HashSet<>();


    protected GenericTrain() {}

    public GenericTrain(Schedule schedule) {
        this.schedule = schedule;
    }

    public Long getId() { return id; }

    public String getNumber() { return number; }

    public void setNumber(String number) { this.number = number; }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public Schedule getSchedule() { return schedule; }

    public void setSchedule(Schedule schedule) { this.schedule = schedule; }

    public Set<RoutePoint> getRoutePoints() { return routePoints; }

    public void setRoutePoints(Set<RoutePoint> routePoints) { this.routePoints = routePoints; }

    public Integer getNumOfPlazkartCars() {
        return numOfPlazkartCars;
    }

    public Integer getNumOfSeatsInPlazkartCar() {
        return numOfSeatsInPlazkartCar;
    }

    public Integer getNumOfCoopeCars() {
        return numOfCoopeCars;
    }

    public Integer getNumOfSeatsInCoopeCar() {
        return numOfSeatsInCoopeCar;
    }

    public Integer getNumOfSwCars() {
        return numOfSwCars;
    }

    public Integer getNumOfSeatsInSwCar() {
        return numOfSeatsInSwCar;
    }

    public void setNumOfPlazkartCars(Integer numOfPlazkartCars) {
        this.numOfPlazkartCars = numOfPlazkartCars;
    }

    public void setNumOfSeatsInPlazkartCar(Integer numOfSeatsInPlazkartCar) {
        this.numOfSeatsInPlazkartCar = numOfSeatsInPlazkartCar;
    }

    public void setNumOfCoopeCars(Integer numOfCoopeCars) {
        this.numOfCoopeCars = numOfCoopeCars;
    }

    public void setNumOfSeatsInCoopeCar(Integer numOfSeatsInCoopeCar) {
        this.numOfSeatsInCoopeCar = numOfSeatsInCoopeCar;
    }

    public void setNumOfSwCars(Integer numOfSwCars) {
        this.numOfSwCars = numOfSwCars;
    }

    public void setNumOfSeatsInSwCar(Integer numOfSeatsInSwCar) {
        this.numOfSeatsInSwCar = numOfSeatsInSwCar;
    }
}
