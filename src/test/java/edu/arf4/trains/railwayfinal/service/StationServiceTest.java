package edu.arf4.trains.railwayfinal.service;

import edu.arf4.trains.railwayfinal.config.DatabaseConfig;
import edu.arf4.trains.railwayfinal.model.Station;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@ContextConfiguration(classes = DatabaseConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class StationServiceTest {

    @Autowired
    private StationService stationService;

    @Test
    @Transactional
    public void createAndDeleteStation() {

        final String NEW_STATION_NAME = "Kingston";
        Long newStationId = stationService.createStation(NEW_STATION_NAME);

        Station newStationFromDB = stationService.getStationById(newStationId, false);
        assertEquals(NEW_STATION_NAME, newStationFromDB.getName());

        stationService.deleteStation(newStationFromDB.getId());
        newStationFromDB = stationService.getStationById(newStationId, false);
        assertNull(newStationFromDB);
    }



//    @Test
//    @Ignore
//    public void createDuplicateStation() {
//
//        simpleServices.createStation(new Station("Moscow"));
//
//        System.out.println("fdfdfdfdfd");
//    }
}