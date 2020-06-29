package edu.arf4.trains.railwayfinal.model;

import util.Constants;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
public class TrainCar {

    @Id
    @GeneratedValue(generator = Constants.MY_ID_GENERATOR)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "train_id")
    private Train train;

    @Enumerated(EnumType.STRING)
    private TrainCarType type;

    @ElementCollection
    @CollectionTable(name = "train_car_seats")
    @MapKeyColumn(name = "seat_number")
    @Column(name = "is_reserved")
    private Map<Integer,Boolean> seats = new HashMap<>();//maybe need List instead of Map with initial cap



    public TrainCarType getType() {
        return type;
    }

    public void setType(TrainCarType type) {
        this.type = type;
    }

    public Map<Integer, Boolean> getSeats() {
        return seats;
    }

    public void setSeats(Map<Integer, Boolean> seats) {
        this.seats = seats;
    }
}
