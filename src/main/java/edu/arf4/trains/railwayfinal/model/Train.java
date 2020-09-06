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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.OrderColumn;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Entity
public class Train {

    @Id
    @GeneratedValue(generator = Constants.MY_ID_GENERATOR)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private GenericTrain genericTrain;

    @Column(nullable = false)
    private LocalDate departDate;

    @OneToMany(mappedBy = "train", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, orphanRemoval = true)
    private Set<TrainCar> trainCars = new HashSet<>();

//    @Size(min = 2)
//    @OneToMany(mappedBy = "train", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, orphanRemoval = true)
//    @org.hibernate.annotations.OrderBy(clause = "depart_datetime")
//    private Set<SpecRoutePoint> specRoutePoints = new HashSet<>();

    @Size(min = 2)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, orphanRemoval = true)
    @JoinColumn(name = "train_id", nullable = false)
    @OrderColumn(name = "order_of_spec_route_point", nullable = false)
    private List<SpecRoutePoint> specRoutePoints = new ArrayList<>();


    @OneToMany(mappedBy = "train", fetch = FetchType.LAZY)
    private Set<Ticket> tickets = new HashSet<>();


    protected Train() {}

    public Train(GenericTrain genericTrain) {
        this.genericTrain = genericTrain;
    }

    public Long getId() {
        return id;
    }

    public GenericTrain getGenericTrain() {
        return genericTrain;
    }

    public void setGenericTrain(GenericTrain genericTrain) {
        this.genericTrain = genericTrain;
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

    public void setSpecRoutePoints(List<SpecRoutePoint> specRoutePoints) {
        this.specRoutePoints = specRoutePoints;
    }

    public List<SpecRoutePoint> getSpecRoutePoints() {
        return specRoutePoints;
    }
}
