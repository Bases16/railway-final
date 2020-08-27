package edu.arf4.trains.railwayfinal.service;

import edu.arf4.trains.railwayfinal.dao.StationDao;
import edu.arf4.trains.railwayfinal.model.Station;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//@Profile({"main", "alter"})
@Service
@Transactional
public class StationService {

    @Autowired
    StationDao stationDao;

    public Long createStation(Station station) {
        return this.stationDao.addStation(station);
    }

}
