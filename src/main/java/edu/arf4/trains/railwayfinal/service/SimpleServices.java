package edu.arf4.trains.railwayfinal.service;


import edu.arf4.trains.railwayfinal.dao.StationDao;
import edu.arf4.trains.railwayfinal.dao.imps.GenericTrainDaoImpl;
import edu.arf4.trains.railwayfinal.model.Station;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SimpleServices {

    // DAO METHODS CALLING SHOULD NOT BE IN CONTROLLERS BUT SHOULD BE IN SERVICES RATHER THAN
    // SERVICE METHODS IN SERVICE METHODS

    public static final Logger log = LoggerFactory.getLogger(SimpleServices.class);

    private final StationDao stationDao;

    public SimpleServices(StationDao stationDao) {
        log.debug("{} WAS CREATED", this.getClass());
        this.stationDao = stationDao;
    }

    @Transactional
    public Long createStation(Station station) {
        return this.stationDao.addStation(station);
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Station getStationById(Long id, boolean getProxy) {
        return this.stationDao.getStationById(id, getProxy);
    }

//    @Transactional(readOnly = true)
//    public Station getStationByName(String name) {
//        return this.stationDao.getStationByName(name);
//    }
}
