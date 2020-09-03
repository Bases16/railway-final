package edu.arf4.trains.railwayfinal.service;

import edu.arf4.trains.railwayfinal.config.DatabaseConfig;
import edu.arf4.trains.railwayfinal.dao.StationDao;
import edu.arf4.trains.railwayfinal.dao.TrainDao;
import edu.arf4.trains.railwayfinal.model.SpecRoutePoint;
import edu.arf4.trains.railwayfinal.model.Station;
import edu.arf4.trains.railwayfinal.model.Train;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

import static org.junit.Assert.*;

//@ContextConfiguration(classes = AlterDatabaseConfig.class)
@ContextConfiguration(classes = DatabaseConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class TicketServiceTest {

    @Autowired
    private TrainDao trainDao;
    @Autowired
    private StationDao stationDao;


    @Test
    @Transactional
    public void test() {  // THE SCOPE OF GUARANTEED IDENTITY

        Train train = trainDao.getTrainById(1L);
        Station station = stationDao.getStationByName("Moscow");

        Set<SpecRoutePoint> srPoints = train.getSpecRoutePoints();

        for (SpecRoutePoint point : srPoints) {
            if (point.getRoutePoint().getStation() == station) {
                System.out.println("point.getRoutePoint().getStation() == station");
                if (point.getRoutePoint().getStation().equals(station)) {
                    System.out.println("point.getRoutePoint().getStation() AND EQUALS TOO");
                }
            }
        }
        System.out.println("DONE");

    }




































}






