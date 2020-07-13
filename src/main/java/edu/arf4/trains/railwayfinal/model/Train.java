package edu.arf4.trains.railwayfinal.model;

import edu.arf4.trains.railwayfinal.util.Constants;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Train {

    @Id
    @GeneratedValue(generator = Constants.MY_ID_GENERATOR)
    private Long id;

    @Column(nullable = false)
    private LocalDate departDate;

    @OneToMany(mappedBy = "train", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, orphanRemoval = true)
    private Set<TrainCar> trainCars = new HashSet<>(Constants.NUM_OF_TRAIN_CARS_IN_TRAIN);

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "spec_route_points")
    private Set<SpecRoutePoint> specRoutePoints = new HashSet<>();


    public Long getId() {
        return id;
    }

    public LocalDate getDepartDate() {
        return departDate;
    }

    public void setDepartDate(LocalDate departDate) {
        this.departDate = departDate;
    }

    public Set<TrainCar> getTrainCars() {
        return trainCars;
    }

    public void setTrainCars(Set<TrainCar> trainCars) {
        this.trainCars = trainCars;
    }
}

