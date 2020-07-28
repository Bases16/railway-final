import edu.arf4.trains.railwayfinal.config.DatabaseConfig;
import edu.arf4.trains.railwayfinal.dao.GenericTrainDao;
import edu.arf4.trains.railwayfinal.dao.StationDao;
import edu.arf4.trains.railwayfinal.dao.TrainDao;
import edu.arf4.trains.railwayfinal.dto.GenericTrainDto;
import edu.arf4.trains.railwayfinal.dto.RoutePointDto;
import edu.arf4.trains.railwayfinal.dto.ScheduleDto;
import edu.arf4.trains.railwayfinal.dto.TrainDto;
import edu.arf4.trains.railwayfinal.model.Example;
import edu.arf4.trains.railwayfinal.model.GenericTrain;
import edu.arf4.trains.railwayfinal.model.Schedule;
import edu.arf4.trains.railwayfinal.model.SpecRoutePoint;
import edu.arf4.trains.railwayfinal.model.Train;
import edu.arf4.trains.railwayfinal.service.GenericTrainService;
import edu.arf4.trains.railwayfinal.service.TrainService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DatabaseConfig.class)
public class TestingAllTrainServices {

    @Autowired
    GenericTrainService genericTrainService;
    @Autowired
    GenericTrainDao genericTrainDao;
    @Autowired
    TrainDao trainDao;
    @Autowired
    TrainService trainService;


    final String RIGHT_GT_NUMBER = "777-AYE";

    @Test
    public void testingGenericTrainService() {

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

        Long newGenTrainId = genericTrainService.createGenericTrain(genericTrainDto);
        GenericTrain genericTrain = genericTrainDao.getGenericTrainByNumber(RIGHT_GT_NUMBER);
//        Schedule retrievedSchedule = genericTrainDao.getScheduleByGenTrainId(newGenTrainId);

        assertNotNull(genericTrain);
        assertEquals(genericTrain.getRoutePoints().size(), 2);
//        assertTrue(retrievedSchedule.getSunday());


        LocalDate start = LocalDate.of(2020, 8, 10);
        LocalDate end = LocalDate.of(2020, 8, 30);
        trainService.registerTrainByGivenDatesAndGenTrain(newGenTrainId, start, end);

        Train train = trainDao.findTrainById(1065L);  // WHHYYYYYY!!??????
        assertNotNull(train.getDepartDate());


        LocalDate st = LocalDate.of(2020, 7, 26);
        LocalDate en = LocalDate.of(2020, 12, 10);

        System.out.println("\n AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAaa \n");
        List<TrainDto> trainDtoList = trainService.getTrainDtoListByStation(15L, st, en);

        for(TrainDto dto : trainDtoList) {
            System.out.println(dto);
        }
        assertEquals(trainDtoList.size(), 12);

        System.out.println(" BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB");
        List<GenericTrainDto> genericTrainDtos = genericTrainService.getAllGenericTrains();
        assertEquals(genericTrainDtos.size(), 3);

        for(GenericTrainDto dto : genericTrainDtos) {
            System.out.println(dto);
        }

        System.out.println(" CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC");

        LocalDate s = LocalDate.of(2020, 11, 30);
        LocalDate e = LocalDate.of(2020, 12, 4);

        List<TrainDto> trainDtos=trainService.getTrainDtoListBy2StationsAndDateRange(15L, 17L, s, e);

        for(TrainDto dto : trainDtos) {
            System.out.println(dto);
        }

        assertEquals(trainDtos.size(), 2);




    }


    @Test
    public void tess() {
        GenericTrain gt = genericTrainDao.getGenericTrainByNumber("1488HH");
        gt.getRoutePoints().iterator().forEachRemaining(v -> System.out.println(v.getOrderOfStation()));
    }

    @Test
    public void TestExample() {

        Long id = trainDao.addExample(new Example(5));

        assertNotNull(id);
        System.out.println("IDDDDDD IS: " + id);

        Example ex = trainDao.findExample(id);

        assertEquals(ex.getMyInt(), 5);
    }




}
