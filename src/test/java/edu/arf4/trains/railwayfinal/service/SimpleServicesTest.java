package edu.arf4.trains.railwayfinal.service;

import edu.arf4.trains.railwayfinal.config.JtaDatabaseConfig;
import edu.arf4.trains.railwayfinal.model.Station;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@ContextConfiguration(classes = JtaDatabaseConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class SimpleServicesTest {

    @Autowired
    private SimpleServices simpleServices;

    @Test
    @Transactional
    public void createStation() {

        final String NEW_STATION_NAME = "Kingston";
        Station station = new Station(NEW_STATION_NAME);

        Long newStationId = simpleServices.createStation(station);

        Station newStationFromDB = simpleServices.getStationById(newStationId, false);
        assertEquals(newStationFromDB.getName(), NEW_STATION_NAME);
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