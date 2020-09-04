package edu.arf4.trains.railwayfinal.model;


import edu.arf4.trains.railwayfinal.util.Constants;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OrderColumn;
import java.util.ArrayList;
import java.util.List;

@Entity
public class SeatsStateAtPoint {

    @Id
    @GeneratedValue(generator = Constants.MY_ID_GENERATOR)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private TrainCar trainCar;

//    @Column(nullable = false)
//    private Integer orderOfStation;

    @ElementCollection
    @CollectionTable(name = "seat_state")
    @OrderColumn(name = "seat_number", nullable = false)
    @Column(name = "seat_state", nullable = false)
    private List<Boolean> seatStates = new ArrayList<>();



}
