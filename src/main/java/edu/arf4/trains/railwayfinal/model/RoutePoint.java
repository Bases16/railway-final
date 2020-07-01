package edu.arf4.trains.railwayfinal.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.OneToOne;
import java.time.LocalTime;

@Embeddable
//@org.hibernate.annotations.Immutable  // can be in embeddable???
public class RoutePoint {

    //may be one class of POINT??
    // различаются только уточненное время-дата у конкретных точек, плюс счетчик билетов оставшихся


    @OneToOne
    @Column(nullable = false)
    private Station station;  //or String stationName ??

    private LocalTime departTime;

    private LocalTime arrivalTime;


    private int daysFromTrainDepartToDepFromHere;

    private int daysFromTrainDepartToArrivHere;


    //maybe could implement in sorted/ordered collection???  then override equals??????
    private int orderOfStation;



}
