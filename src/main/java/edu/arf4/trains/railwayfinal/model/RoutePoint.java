package edu.arf4.trains.railwayfinal.model;

import edu.arf4.trains.railwayfinal.util.Constants;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalTime;
import java.util.List;

@Entity
public class RoutePoint {


    @Id
    @GeneratedValue(generator = Constants.MY_ID_GENERATOR)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private GenericTrain genericTrain;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Station station;

    @Column(nullable = false)
    private Integer orderOfStation;

    private LocalTime departTime;

    private LocalTime arrivalTime;

    private Integer daysFromTrainDepartToDepartFromHere;

    private Integer daysFromTrainDepartToArrivalHere;

    public Long getId() {
        return id;
    }

    public GenericTrain getGenericTrain() {
        return genericTrain;
    }

    public void setGenericTrain(GenericTrain genericTrain) {
        this.genericTrain = genericTrain;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public Integer getOrderOfStation() {
        return orderOfStation;
    }

    public void setOrderOfStation(Integer orderOfStation) {
        this.orderOfStation = orderOfStation;
    }

    public LocalTime getDepartTime() {
        return departTime;
    }

    public void setDepartTime(LocalTime departTime) {
        this.departTime = departTime;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Integer getDaysFromTrainDepartToDepartFromHere() {
        return daysFromTrainDepartToDepartFromHere;
    }

    public void setDaysFromTrainDepartToDepartFromHere(Integer daysFromTrainDepartToDepartFromHere) {
        this.daysFromTrainDepartToDepartFromHere = daysFromTrainDepartToDepartFromHere;
    }

    public Integer getDaysFromTrainDepartToArrivalHere() {
        return daysFromTrainDepartToArrivalHere;
    }

    public void setDaysFromTrainDepartToArrivalHere(Integer daysFromTrainDepartToArrivalHere) {
        this.daysFromTrainDepartToArrivalHere = daysFromTrainDepartToArrivalHere;
    }

}
