package edu.arf4.trains.railwayfinal.service;

import edu.arf4.trains.railwayfinal.config.JtaDatabaseConfig;
import edu.arf4.trains.railwayfinal.dao.GenericTrainDao;
import edu.arf4.trains.railwayfinal.dao.StationDao;
import edu.arf4.trains.railwayfinal.dao.TrainDao;
import edu.arf4.trains.railwayfinal.model.GenericTrain;
import edu.arf4.trains.railwayfinal.model.RoutePoint;
import edu.arf4.trains.railwayfinal.model.SpecRoutePoint;
import edu.arf4.trains.railwayfinal.model.Station;
import edu.arf4.trains.railwayfinal.model.Train;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

//@ContextConfiguration(classes = AlterDatabaseConfig.class)
@ContextConfiguration(classes = JtaDatabaseConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class TicketServiceTest {

    @Autowired
    private TrainDao trainDao;
    @Autowired
    private StationDao stationDao;
    @Autowired
    private GenericTrainDao genericTrainDao;





    @Test
    @Transactional
    public void buyTicket() {





    }




    @Test
    @Transactional
    @Ignore
    public void test() {  // THE SCOPE OF GUARANTEED IDENTITY

        Train train = trainDao.getTrainById(1L);
        Station station = stationDao.getStationByName("Moscow");

        List<SpecRoutePoint> srPoints = train.getSpecRoutePoints();

        for (SpecRoutePoint point : srPoints) {

            System.out.println(point.getRoutePoint().getStation().getName() );

//            if (point.getRoutePoint().getStation() == station) {
//                System.out.println("point.getRoutePoint().getStation() == station");
//                if (point.getRoutePoint().getStation().equals(station)) {
//                    System.out.println("point.getRoutePoint().getStation() AND EQUALS TOO");
//                }
//            }
        }
        System.out.println("NEXT");

        GenericTrain genericTrain = genericTrainDao.getGenericTrainById(2L);
        List<RoutePoint> routePoints = genericTrain.getRoutePoints();
        for (RoutePoint point : routePoints) {
            System.out.println(point.getStation().getName());
        }

    }




































}






