package edu.arf4.trains.railwayfinal.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Future;
import java.time.LocalDateTime;

@Embeddable
public class SpecRoutePoint {

    @Future
    private LocalDateTime departDatetime;

    @Future
    private LocalDateTime arrivalDatetime;

    @Column(nullable = false)
    private Integer ticketsLeft;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private RoutePoint routePoint;

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

    public Integer getTicketsLeft() {
        return ticketsLeft;
    }

    public void setTicketsLeft(Integer ticketsLeft) {
        this.ticketsLeft = ticketsLeft;
    }

    public RoutePoint getRoutePoint() {
        return routePoint;
    }

    public void setRoutePoint(RoutePoint routePoint) {
        this.routePoint = routePoint;
    }
}
