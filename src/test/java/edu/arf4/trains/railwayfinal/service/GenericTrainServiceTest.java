package edu.arf4.trains.railwayfinal.service;

import edu.arf4.trains.railwayfinal.config.AlterDatabaseConfig;
import edu.arf4.trains.railwayfinal.config.DatabaseConfig;
import edu.arf4.trains.railwayfinal.dao.GenericTrainDao;
import edu.arf4.trains.railwayfinal.dto.GenericTrainDto;
import edu.arf4.trains.railwayfinal.dto.RoutePointDto;
import edu.arf4.trains.railwayfinal.dto.ScheduleDto;
import edu.arf4.trains.railwayfinal.model.GenericTrain;
import edu.arf4.trains.railwayfinal.model.Schedule;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = DatabaseConfig.class)
@ContextConfiguration(classes = AlterDatabaseConfig.class)
public class GenericTrainServiceTest {

    @Autowired
    GenericTrainService genericTrainService;
    @Autowired
    GenericTrainDao genericTrainDao;

    final String RIGHT_GT_NUMBER = "777-AYE";

    @Test
//    @Transactional
    public void createGenericTrain() {

        GenericTrainDto genericTrainDto = new GenericTrainDto();

        genericTrainDto.setNumber(RIGHT_GT_NUMBER);
        genericTrainDto.setRoute("New-York - Astana");
        genericTrainDto.setNumOfPlazkartCars(2);
        genericTrainDto.setNumOfSeatsInPlazkartCar(4);
        genericTrainDto.setNumOfCoopeCars(2);
        genericTrainDto.setNumOfSeatsInCoopeCar(2);
        genericTrainDto.setNumOfSwCars(2);
        genericTrainDto.setNumOfSeatsInSwcar(1);

        ScheduleDto scheduleDto = new ScheduleDto();
        scheduleDto.setWeekPeriodicity(2);
        scheduleDto.setMonday(true);
        scheduleDto.setTuesday(false);
        scheduleDto.setWednesday(true);
        scheduleDto.setThursday(false);
        scheduleDto.setFriday(true);
        scheduleDto.setSaturday(false);
        scheduleDto.setSunday(true);

        genericTrainDto.setSchedule(scheduleDto);

        Set<RoutePointDto> routePointDtoSet = new HashSet<>(2);

        RoutePointDto pointDto1 = new RoutePointDto();
        RoutePointDto pointDto2 = new RoutePointDto();

        pointDto1.setStationId(13); //New-York
        pointDto1.setOrderOfStation(1);
        pointDto1.setDepartTime("10:30");
        pointDto1.setArrivalTime(null);
        pointDto1.setDaysFromTrainDepartToDepartFromHere(0);
        pointDto1.setDaysFromTrainDepartToArrivalHere(null);

        pointDto2.setStationId(15); //Myshkin
        pointDto2.setOrderOfStation(2);
        pointDto2.setDepartTime(null);
        pointDto2.setArrivalTime("15:45");
        pointDto2.setDaysFromTrainDepartToDepartFromHere(null);
        pointDto2.setDaysFromTrainDepartToArrivalHere(2);

        routePointDtoSet.add(pointDto1);
        routePointDtoSet.add(pointDto2);

        genericTrainDto.setRoutePointDtoSet(routePointDtoSet);

//        Long newGenTrainId = genericTrainService.createGenericTrain(genericTrainDto);
        GenericTrain genericTrain = genericTrainDao.getGenericTrainById(2L);

        assertNotNull(genericTrain);
//        assertEquals(genericTrain.getRoutePoints().size(), 2);


    }


}