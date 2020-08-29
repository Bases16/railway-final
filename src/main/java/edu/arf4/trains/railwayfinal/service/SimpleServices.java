package edu.arf4.trains.railwayfinal.service;


import edu.arf4.trains.railwayfinal.dao.StationDao;
import edu.arf4.trains.railwayfinal.model.Station;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SimpleServices {

    @Autowired
    private StationDao stationDao;
//    private final StationDao stationDao;

//    @Autowired
//    public SimpleServices(StationDao stationDao) {
//        this.stationDao = stationDao;
//    }

    @Transactional
    public Long createStation(Station station) {
        return this.stationDao.addStation(station);
    }

    @Transactional(readOnly = true)
    public Station getStationById(Long id, boolean getProxy) {
        return this.stationDao.getStationById(id, getProxy);
    }
}
