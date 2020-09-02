package edu.arf4.trains.railwayfinal.dto;

public class TrainDto {

    private Long id;
    private String number;
    private String globalRoute;
    private String localRoute;
    private String localSrcArrivalDateTime;
    private String localSrcDepartDateTime;
    private String localDstArrivalDateTime;


    public TrainDto() {}

    @Override
    public String toString() {
        return "TrainDto{" +
                "number='" + number + '\'' +
                "\n globalRoute='" + globalRoute + '\'' +
                "\n localRoute='" + localRoute + '\'' +
                "\n localSrcArrivalDateTime='" + localSrcArrivalDateTime + '\'' +
                "\n localSrcDepartDateTime='" + localSrcDepartDateTime + '\'' +
                "\n localDstArrivalDateTime='" + localDstArrivalDateTime + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getGlobalRoute() {
        return globalRoute;
    }

    public void setGlobalRoute(String globalRoute) {
        this.globalRoute = globalRoute;
    }

    public String getLocalSrcDepartDateTime() {
        return localSrcDepartDateTime;
    }

    public void setLocalSrcDepartDateTime(String localSrcDepartDateTime) {
        this.localSrcDepartDateTime = localSrcDepartDateTime;
    }

    public String getLocalSrcArrivalDateTime() {
        return localSrcArrivalDateTime;
    }

    public void setLocalSrcArrivalDateTime(String localSrcArrivalDateTime) {
        this.localSrcArrivalDateTime = localSrcArrivalDateTime;
    }

    public String getLocalRoute() {
        return localRoute;
    }

    public void setLocalRoute(String localRoute) {
        this.localRoute = localRoute;
    }

    public String getLocalDstArrivalDateTime() {
        return localDstArrivalDateTime;
    }

    public void setLocalDstArrivalDateTime(String localDstArrivalDateTime) {
        this.localDstArrivalDateTime = localDstArrivalDateTime;
    }
}
