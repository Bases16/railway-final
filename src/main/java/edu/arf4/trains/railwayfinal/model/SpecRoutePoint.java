package edu.arf4.trains.railwayfinal.model;

import edu.arf4.trains.railwayfinal.util.Constants;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Future;
import java.time.LocalDateTime;

@Entity
@Table(name = "spec_route_points")
public class SpecRoutePoint {

    @Id
    @GeneratedValue(generator = Constants.MY_ID_GENERATOR)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "train_id", updatable = false, insertable = false)
    private Train train;

    @Future
    private LocalDateTime departDatetime;
    @Future
    private LocalDateTime arrivalDatetime;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private RoutePoint routePoint;


    protected SpecRoutePoint() {}



    public SpecRoutePoint(Train train) {
        this.train = train;
    }

    public Long getId() {
        return id;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public LocalDateTime getDepartDatetime() {
        return departDatetime;
    }

    public void setDepartDatetime(LocalDateTime departDatetime) {
        this.departDatetime = departDatetime;
    }

    public LocalDateTime getArrivalDatetime() {
        return arrivalDatetime;
    }

    public void setArrivalDatetime(LocalDateTime arrivalDatetime) {
        this.arrivalDatetime = arrivalDatetime;
    }

    public RoutePoint getRoutePoint() {
        return routePoint;
    }

    public void setRoutePoint(RoutePoint routePoint) {
        this.routePoint = routePoint;
    }
}
