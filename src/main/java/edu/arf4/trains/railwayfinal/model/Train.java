package edu.arf4.trains.railwayfinal.model;

import edu.arf4.trains.railwayfinal.util.Constants;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
public class Train {

    @Id
    @GeneratedValue(generator = Constants.MY_ID_GENERATOR)
    private Long id;

    private LocalDate departDate;


    //not afraid of orphanRemoval because nothing will reference to traincar except Train
    @OneToMany(mappedBy = "train", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<TrainCar> trainCars = new ArrayList<>(Constants.NUM_OF_TRAIN_CARS_IN_TRAIN);


    @ElementCollection
    //@Column(nullable = false)
    private Set<SpecRoutePoint> specRoutePoints; // ... = new ...





    public LocalDate getDepartDate() {
        return departDate;
    }

    public void setDepartDate(LocalDate departDate) {
        this.departDate = departDate;
    }

    public List<TrainCar> getTrainCars() {
        return trainCars;
    }

    public void setTrainCars(List<TrainCar> trainCars) {
        this.trainCars = trainCars;
    }
}

