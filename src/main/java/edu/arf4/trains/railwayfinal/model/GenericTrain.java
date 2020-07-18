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
public class GenericTrain {

    @Id
    @GeneratedValue(generator = Constants.MY_ID_GENERATOR)
    private Long id;

    @Column(nullable = false, unique = true)
    private String number;

    private final Integer numOfPlazkartCars = Constants.NUM_OF_PLAZKART_CARS;
    private final Integer numOfSeatsInPlazkartCar = Constants.NUM_OF_SEATS_IN_PLAZKART_CAR;

    private final Integer numOfCoopeCars = Constants.NUM_OF_COOPE_CARS;
    private final Integer numOfSeatsInCoopeCar = Constants.NUM_OF_SEATS_IN_COOPE_CAR;

    private final Integer numOfSwCars = Constants.NUM_OF_SW_CARS;
    private final Integer numOfSeatsInSwCar = Constants.NUM_OF_SEATS_IN_SW_CAR;

    @Embedded
    private Schedule schedule;

    @Size(min = 2)
    @OneToMany(mappedBy = "genericTrain", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, orphanRemoval = false)
    private Set<RoutePoint> routePoints = new HashSet<>();


    protected GenericTrain() {}

    public GenericTrain(Schedule schedule) {
        this.schedule = schedule;
    }

    //  getters/setters
    public Long getId() { return id; }

    public String getNumber() { return number; }

    public void setNumber(String number) { this.number = number; }

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
}
