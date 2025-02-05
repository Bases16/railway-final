package edu.arf4.trains.railwayfinal.model;

import edu.arf4.trains.railwayfinal.util.Constants;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "train_cars")
public class TrainCar {

    @Id
    @GeneratedValue(generator = Constants.MY_ID_GENERATOR)
    private Long id;

    @Enumerated(EnumType.STRING)
    @NotNull
    private TrainCarType type;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "train_id", updatable = false, insertable = false)
    private Train train;

    @NotEmpty
    @OneToMany(cascade = CascadeType.PERSIST, orphanRemoval = true)
    @JoinColumn(name = "train_car_id", nullable = false) // why joincolumn here? because it's the OWNER!
    @OrderColumn(name = "order_of_station", nullable = false)
    private List<SeatsStateAtPoint> seatsStateAtCar = new ArrayList<>();



    protected TrainCar() {}

    public TrainCar(TrainCarType type) {
        this.type = type;
    }

    public TrainCarType getType() {
        return type;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public List<SeatsStateAtPoint> getSeatsStateAtCar() {
        return seatsStateAtCar;
    }

}
