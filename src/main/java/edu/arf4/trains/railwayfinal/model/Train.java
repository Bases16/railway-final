package edu.arf4.trains.railwayfinal.model;

import util.Constants;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Train {

    @Id
    @GeneratedValue(generator = Constants.MY_ID_GENERATOR)
    private Long id;

    private LocalDate departDate;

    @OneToMany(mappedBy = "train")
    private List<TrainCar> trainCars = new ArrayList<>(Constants.NUM_OF_TRAIN_CARS_IN_TRAIN);


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

