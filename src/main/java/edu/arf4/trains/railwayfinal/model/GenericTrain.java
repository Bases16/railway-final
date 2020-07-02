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

    @OneToMany(mappedBy = "genericTrain", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, orphanRemoval = false)
    private Set<RoutePoint> routePoints = new HashSet<>();


}
