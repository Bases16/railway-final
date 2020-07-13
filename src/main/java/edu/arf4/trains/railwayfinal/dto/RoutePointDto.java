package edu.arf4.trains.railwayfinal.dto;

import javax.persistence.Column;

public class RoutePointDto {



    //private String GenericTrain;

    private String station;
    private String orderOfStation;
    private String departTime;
    private String arrivalTime;
    private String daysFromTrainDepartToDepartFromHere;
    private String daysFromTrainDepartToArrivalHere;


    public String getStation() {
        return station;
    }

    public String getOrderOfStation() {
        return orderOfStation;
    }

    public String getDepartTime() {
        return departTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public String getDaysFromTrainDepartToDepartFromHere() {
        return daysFromTrainDepartToDepartFromHere;
    }

    public String getDaysFromTrainDepartToArrivalHere() {
        return daysFromTrainDepartToArrivalHere;
    }
}
