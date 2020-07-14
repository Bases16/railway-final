package edu.arf4.trains.railwayfinal.dao;

import edu.arf4.trains.railwayfinal.model.Station;

public interface StationDao {



    Station getStationProxyById(Long id);

    Station getStationById(Long id);


}
