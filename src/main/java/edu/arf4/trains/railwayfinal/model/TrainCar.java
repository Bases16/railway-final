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
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Entity
public class TrainCar {

    @Id
    @GeneratedValue(generator = Constants.MY_ID_GENERATOR)
    private Long id;

    @Column(nullable = false)
    private Integer orderOfCar;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TrainCarType type;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Train train;

    @NotEmpty
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "train_car_seats")
    @MapKeyColumn(name = "seat_number")
    @Column(name = "is_reserved", nullable = false)
    @org.hibernate.annotations.OrderBy(clause = "seat_number ASC")
    private Map<Integer,Boolean> seats = new HashMap<>(); // alt.: @SortNatural, TreeMap .   WAS LinkedHashMap


//    @NotEmpty
    @OneToMany(mappedBy = "trainCar", fetch = FetchType.LAZY)
    @OrderColumn(name = "order_of_station", nullable = false)
    private List<SeatsStateAtPoint> seatsStateAtPoints = new ArrayList<>();



    protected TrainCar() {}

    public TrainCar(Integer orderOfCar, TrainCarType type) {
        this.orderOfCar = orderOfCar;
        this.type = type;
    }

    public TrainCarType getType() {
        return type;
    }

    public Map<Integer, Boolean> getSeats() {
        return seats;
    }

    public void setSeats(Map<Integer, Boolean> seats) {
        this.seats = seats;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public Integer getOrderOfCar() {
        return orderOfCar;
    }

}
