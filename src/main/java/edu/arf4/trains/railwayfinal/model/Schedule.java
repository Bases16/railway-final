package edu.arf4.trains.railwayfinal.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Schedule {

    @Column(nullable = false)
    private Integer weekPeriodicity;

    @Column(nullable = false)
    private Boolean monday;
    @Column(nullable = false)
    private Boolean tuesday;
    @Column(nullable = false)
    private Boolean wednesday;
    @Column(nullable = false)
    private Boolean thursday;
    @Column(nullable = false)
    private Boolean friday;
    @Column(nullable = false)
    private Boolean saturday;
    @Column(nullable = false)
    private Boolean sunday;

}
