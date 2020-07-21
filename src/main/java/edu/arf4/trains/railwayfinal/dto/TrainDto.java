package edu.arf4.trains.railwayfinal.dto;

public class TrainDto {

    // number
    //  Moscow - Adler
    //  departDateTime  (if not null)
    //  arrivalDateTime (if not null)

    private String number;
    private String route;
    private String departDateTime;
    private String arrivalDateTime;


    @Override
    public String toString() {
        return "TrainDto{" +
                "number='" + number + '\'' +
                ", route='" + route + '\'' +
                ", departDateTime='" + departDateTime + '\'' +
                ", arrivalDateTime='" + arrivalDateTime + '\'' +
                '}';
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getDepartDateTime() {
        return departDateTime;
    }

    public void setDepartDateTime(String departDateTime) {
        this.departDateTime = departDateTime;
    }

    public String getArrivalDateTime() {
        return arrivalDateTime;
    }

    public void setArrivalDateTime(String arrivalDateTime) {
        this.arrivalDateTime = arrivalDateTime;
    }
}
