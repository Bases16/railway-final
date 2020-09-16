package edu.arf4.trains.railwayfinal.model;

import edu.arf4.trains.railwayfinal.util.Constants;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Ticket {

    @Id
    @GeneratedValue(generator = Constants.MY_ID_GENERATOR)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Train train;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Passenger passenger;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Station stationFrom;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Station stationTo;

    @NotNull
    private LocalDateTime departureDateTime;

    @NotNull
    private LocalDateTime arrivalDateTime;

    @NotNull
    private Integer numberOfTrainCar;

    @NotNull
    private Integer numberOfSeat;

    @Column(nullable = true)
    private Float cost;


    public Long getId() {
        return id;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public Station getStationFrom() {
        return stationFrom;
    }

    public void setStationFrom(Station stationFrom) {
        this.stationFrom = stationFrom;
    }

    public Station getStationTo() {
        return stationTo;
    }

    public void setStationTo(Station stationTo) {
        this.stationTo = stationTo;
    }

    public LocalDateTime getDepartureDateTime() {
        return departureDateTime;
    }

    public void setDepartureDateTime(LocalDateTime departureDateTime) {
        this.departureDateTime = departureDateTime;
    }

    public LocalDateTime getArrivalDateTime() {
        return arrivalDateTime;
    }

    public void setArrivalDateTime(LocalDateTime arrivalDateTime) {
        this.arrivalDateTime = arrivalDateTime;
    }

    public Float getCost() {
        return cost;
    }

    public void setCost(Float cost) {
        this.cost = cost;
    }

    public Integer getNumberOfSeat() {
        return numberOfSeat;
    }

    public void setNumberOfSeat(Integer numberOfSeat) {
        this.numberOfSeat = numberOfSeat;
    }

    public Integer getNumberOfTrainCar() {
        return numberOfTrainCar;
    }

    public void setNumberOfTrainCar(Integer numberOfTrainCar) {
        this.numberOfTrainCar = numberOfTrainCar;
    }
}