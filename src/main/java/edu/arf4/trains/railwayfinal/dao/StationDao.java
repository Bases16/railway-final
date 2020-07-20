package edu.arf4.trains.railwayfinal.dao;

import edu.arf4.trains.railwayfinal.model.Station;

public interface StationDao {


    Station getStationById(Long id, boolean getProxy);

    Long addStation(Station station);


}
