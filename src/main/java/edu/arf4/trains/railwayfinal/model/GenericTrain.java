package edu.arf4.trains.railwayfinal.model;

import edu.arf4.trains.railwayfinal.util.Constants;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class GenericTrain {

    @Id
    @GeneratedValue(generator = Constants.MY_ID_GENERATOR)
    private Long id;
    private String number;

    private final Integer numOfPlazkartCars = Constants.NUM_OF_PLAZKART_CARS;
    private final Integer numOfSeatsInPlazkartCar = Constants.NUM_OF_SEATS_IN_PLAZKART_CAR;

    private final Integer numOfCoopeCars = Constants.NUM_OF_COOPE_CARS;
    private final Integer numOfSeatsInCoopeCar = Constants.NUM_OF_SEATS_IN_COOPE_CAR;

    private final Integer numOfSVCars = Constants.NUM_OF_SV_CARS;
    private final Integer numOfSeatsInSVCar = Constants.NUM_OF_SEATS_IN_SV_CAR;


    @ElementCollection
    private Set<RoutePoint> routePoints = new HashSet<>();  //else  SortedSet



}
