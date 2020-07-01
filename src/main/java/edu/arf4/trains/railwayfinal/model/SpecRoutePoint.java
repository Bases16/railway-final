package edu.arf4.trains.railwayfinal.model;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable
public class SpecRoutePoint {


    private LocalDateTime departDateTime;

    private LocalDateTime arrivDateTime;

    private Integer ticketsLeft;


    //the other fields in abstract RoutePoint

}
