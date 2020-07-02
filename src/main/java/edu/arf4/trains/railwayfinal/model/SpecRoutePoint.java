package edu.arf4.trains.railwayfinal.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Embeddable
public class SpecRoutePoint {

    private LocalDateTime departDatetime;

    private LocalDateTime arrivalDatetime;

    @Column(nullable = false)
    private Integer ticketsLeft;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private RoutePoint extraPointData;

}
