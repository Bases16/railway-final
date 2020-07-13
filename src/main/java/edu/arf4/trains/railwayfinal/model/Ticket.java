package edu.arf4.trains.railwayfinal.model;

import edu.arf4.trains.railwayfinal.util.Constants;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
public class Ticket {

    @Id
    @GeneratedValue(generator = Constants.MY_ID_GENERATOR)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Train train;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Passenger passenger;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Station stationFrom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Station stationTo;

    @Column(nullable = false)
    private LocalDateTime departureDateTime;

    @Column(nullable = false)
    private LocalDateTime arrivalDateTime;

    @Column(nullable = false)
    private Float cost;



}