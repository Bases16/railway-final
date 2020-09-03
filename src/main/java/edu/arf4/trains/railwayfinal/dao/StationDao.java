package edu.arf4.trains.railwayfinal.dao;

import edu.arf4.trains.railwayfinal.model.SpecRoutePoint;
import edu.arf4.trains.railwayfinal.model.Station;

import java.util.List;

public interface StationDao {


    Station getStationById(Long id, boolean getProxy);

    Long addStation(Station station);


    Station getStationByName(String name);
}
