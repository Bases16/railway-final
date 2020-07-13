package edu.arf4.trains.railwayfinal.model;

import edu.arf4.trains.railwayfinal.util.Constants;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalTime;

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

    @Column(nullable = false)
    private Integer daysFromTrainDepartToDepartFromHere;

    @Column(nullable = false)
    private Integer daysFromTrainDepartToArrivalHere;

//    public RoutePoint(GenericTrain genericTrain) {
//        this.genericTrain = genericTrain;
//    }

}
