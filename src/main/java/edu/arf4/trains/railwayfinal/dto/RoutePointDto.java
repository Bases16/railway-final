package edu.arf4.trains.railwayfinal.dto;

public class RoutePointDto {

    //private String GenericTrain;

    private long stationId;
    private int orderOfStation;
    private String departTime;
    private String arrivalTime;
    private int daysFromTrainDepartToDepartFromHere;
    private int daysFromTrainDepartToArrivalHere;


    public long getStationId() {
        return stationId;
    }

    public void setStationId(long stationId) {
        this.stationId = stationId;
    }

    public int getOrderOfStation() {
        return orderOfStation;
    }

    public void setOrderOfStation(int orderOfStation) {
        this.orderOfStation = orderOfStation;
    }

    public String getDepartTime() {
        return departTime;
    }

    public void setDepartTime(String departTime) {
        this.departTime = departTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getDaysFromTrainDepartToDepartFromHere() {
        return daysFromTrainDepartToDepartFromHere;
    }

    public void setDaysFromTrainDepartToDepartFromHere(int daysFromTrainDepartToDepartFromHere) {
        this.daysFromTrainDepartToDepartFromHere = daysFromTrainDepartToDepartFromHere;
    }

    public int getDaysFromTrainDepartToArrivalHere() {
        return daysFromTrainDepartToArrivalHere;
    }

    public void setDaysFromTrainDepartToArrivalHere(int daysFromTrainDepartToArrivalHere) {
        this.daysFromTrainDepartToArrivalHere = daysFromTrainDepartToArrivalHere;
    }
}
