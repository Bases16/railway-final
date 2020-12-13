package edu.arf4.trains.railwayfinal.model;

import edu.arf4.trains.railwayfinal.util.Constants;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "generic_trains")
@org.hibernate.annotations.BatchSize(size = 9)
public class GenericTrain {

    @Id
    @GeneratedValue(generator = Constants.MY_ID_GENERATOR)
    private Long id;

    @NotNull
    @Column(unique = true)
    private String number;

    @NotNull
    private String route;

    @NotNull
//    @Column(nullable = false)
    private Integer numOfPlazkartCars;
    @NotNull
    private Integer numOfSeatsInPlazkartCar;
    @NotNull
    private Integer numOfCoopeCars;
    @NotNull
    private Integer numOfSeatsInCoopeCar;
    @NotNull
    private Integer numOfSwCars;
    @NotNull
    private Integer numOfSeatsInSwCar;

    @Embedded
    private Schedule schedule;

//    @Size(min = 2)
//    @org.hibernate.annotations.OrderBy(clause = "days_from_train_depart_to_depart_from_here, depart_time")
//    @org.hibernate.annotations.OrderBy(clause = "order_of_station DESC")
//    @org.hibernate.annotations.Fetch(org.hibernate.annotations.FetchMode.SUBSELECT)
//    @OneToMany(mappedBy = "genericTrain", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
//    private Set<RoutePoint> routePoints = new HashSet<>();

    @Size(min = 2)
    @org.hibernate.annotations.Fetch(org.hibernate.annotations.FetchMode.SUBSELECT)
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "generic_train_id", nullable = false)
    @OrderColumn(name = "order_of_route_point", nullable = false)
    private List<RoutePoint> routePoints = new ArrayList<>();



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

    public List<RoutePoint> getRoutePoints() {
        return routePoints;
    }

    public void setRoutePoints(List<RoutePoint> routePoints) {
        this.routePoints = routePoints;
    }

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
