package edu.arf4.trains.railwayfinal.model;


import edu.arf4.trains.railwayfinal.util.Constants;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
    @JoinColumn(name = "train_car_id", updatable = false, insertable = false) // todo DELETE THE FIELD AT ALL, MAKE IT UNI ??
    private TrainCar trainCar; // todo remove the field ??

    @ElementCollection(fetch = FetchType.EAGER) // todo might set up EAGER ?
    @CollectionTable(name = "seat_state")
    @OrderColumn(name = "seat_number", nullable = false)
    @Column(name = "seat_state", nullable = false)
    private List<Boolean> seatStates = new ArrayList<>();



    public SeatsStateAtPoint(int numOfSeatsInCar) {
        for (int i = 1; i <= numOfSeatsInCar; i++) {
            this.seatStates.add(Boolean.FALSE);
        }
    }

    protected SeatsStateAtPoint() {}

    public Long getId() {  // TODO WHY getId is highlighted as if being used somewhere ??
        return id;
    }

    public TrainCar getTrainCar() {
        return trainCar;
    }

    public void setTrainCar(TrainCar trainCar) {
        this.trainCar = trainCar;
    }

    public List<Boolean> getSeatStates() {
        return seatStates;
    }

}
