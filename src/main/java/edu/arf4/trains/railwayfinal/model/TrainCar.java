package edu.arf4.trains.railwayfinal.model;

import edu.arf4.trains.railwayfinal.util.Constants;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
import java.util.LinkedHashMap;
import java.util.Map;

@Entity
//@org.hibernate.annotations.Immutable //Can be immutable if we change SEATS separately ??? //  NO SETTERS
public class TrainCar {

    @Id
    @GeneratedValue(generator = Constants.MY_ID_GENERATOR)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Train train;

    @Enumerated(EnumType.STRING)
    private TrainCarType type;

    @ElementCollection
    @CollectionTable(name = "train_car_seats")
    @MapKeyColumn(name = "seat_number")
    @Column(name = "is_reserved")
    @org.hibernate.annotations.OrderBy(clause = "seat_number asc") //тогда Map = new LinkedHashMap
    private Map<Integer,Boolean> seats = new LinkedHashMap<>();//maybe need ordered List instead of Map with initial cap




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
