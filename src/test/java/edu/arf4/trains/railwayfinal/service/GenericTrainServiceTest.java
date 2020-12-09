package edu.arf4.trains.railwayfinal.service;

import edu.arf4.trains.railwayfinal.config.JtaDatabaseConfig;
import edu.arf4.trains.railwayfinal.dao.GenericTrainDao;
import edu.arf4.trains.railwayfinal.dto.GenericTrainDto;
import edu.arf4.trains.railwayfinal.dto.RoutePointDto;
import edu.arf4.trains.railwayfinal.dto.ScheduleDto;
import edu.arf4.trains.railwayfinal.model.GenericTrain;
import edu.arf4.trains.railwayfinal.model.RoutePoint;
import edu.arf4.trains.railwayfinal.model.Schedule;
import edu.arf4.trains.railwayfinal.util.Converter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;

@ContextConfiguration(classes = JtaDatabaseConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class GenericTrainServiceTest {

    @Autowired
    private GenericTrainService genericTrainService;
    @Autowired
    private GenericTrainDao genericTrainDao;


    private GenericTrainDto createTestGenTrainDto() {

        final String GT_NUMBER = "777-AYE";
        final String ROUTE = "New-York - Myshkin";

        GenericTrainDto genTrainDto = new GenericTrainDto();

        genTrainDto.setNumber(GT_NUMBER);
        genTrainDto.setRoute(ROUTE);
        genTrainDto.setNumOfPlazkartCars(2);
        genTrainDto.setNumOfSeatsInPlazkartCar(4);
        genTrainDto.setNumOfCoopeCars(2);
        genTrainDto.setNumOfSeatsInCoopeCar(2);
        genTrainDto.setNumOfSwCars(2);
        genTrainDto.setNumOfSeatsInSwcar(1);

        ScheduleDto scheduleDto = new ScheduleDto();
        scheduleDto.setWeekPeriodicity(2);
        scheduleDto.setMonday(true);
        scheduleDto.setTuesday(false);
        scheduleDto.setWednesday(true);
        scheduleDto.setThursday(false);
        scheduleDto.setFriday(true);
        scheduleDto.setSaturday(false);
        scheduleDto.setSunday(true);

        genTrainDto.setSchedule(scheduleDto);

        List<RoutePointDto> routePointDtoList = new LinkedList<>();

        RoutePointDto pointDto1 = new RoutePointDto();

        pointDto1.setStationId(13);      //New-York
        pointDto1.setDepartTime("10:30");
        pointDto1.setArrivalTime(null);
        pointDto1.setDaysFromTrainDepartToDepartFromHere(0);
        pointDto1.setDaysFromTrainDepartToArrivalHere(null);

        RoutePointDto pointDto2 = new RoutePointDto();
        pointDto2.setStationId(15);      //Myshkin
        pointDto2.setDepartTime(null);
        pointDto2.setArrivalTime("15:45");
        pointDto2.setDaysFromTrainDepartToDepartFromHere(null);
        pointDto2.setDaysFromTrainDepartToArrivalHere(2);

        routePointDtoList.add(pointDto1);
        routePointDtoList.add(pointDto2);

        genTrainDto.setRoutePointDtoList(routePointDtoList);

        return genTrainDto;
    }

    private void checkEntityAndDtoForAccordance(GenericTrain genTrain, GenericTrainDto genTrainDto) {

        ScheduleDto scheduleDto = genTrainDto.getSchedule();

        assertEquals(genTrain.getRoutePoints().size(), genTrainDto.getRoutePointDtoList().size());

        assertEquals(genTrain.getNumber(), genTrainDto.getNumber());
        assertEquals(genTrain.getRoute(), genTrainDto.getRoute());
        assertEquals(genTrain.getNumOfPlazkartCars(), (Integer) genTrainDto.getNumOfPlazkartCars());
        assertEquals(genTrain.getNumOfSeatsInPlazkartCar(), (Integer) genTrainDto.getNumOfSeatsInPlazkartCar());
        assertEquals(genTrain.getNumOfCoopeCars(), (Integer) genTrainDto.getNumOfCoopeCars());
        assertEquals(genTrain.getNumOfSeatsInCoopeCar(), (Integer) genTrainDto.getNumOfSeatsInCoopeCar());
        assertEquals(genTrain.getNumOfSwCars(), (Integer) genTrainDto.getNumOfSwCars());
        assertEquals(genTrain.getNumOfSeatsInSwCar(), (Integer) genTrainDto.getNumOfSeatsInSwcar());

        Schedule schedule = genTrain.getSchedule();
        assertEquals(schedule.getWeekPeriodicity(), (Integer) scheduleDto.getWeekPeriodicity());
        assertEquals(schedule.getMonday(), scheduleDto.getMonday());
        assertEquals(schedule.getTuesday(), scheduleDto.getTuesday());
        assertEquals(schedule.getWednesday(), scheduleDto.getWednesday());
        assertEquals(schedule.getThursday(), scheduleDto.getThursday());
        assertEquals(schedule.getFriday(), scheduleDto.getFriday());
        assertEquals(schedule.getSaturday(), scheduleDto.getSaturday());
        assertEquals(schedule.getSunday(), scheduleDto.getSunday());

        RoutePointDto rpDto = genTrainDto.getRoutePointDtoList().get(1);
        RoutePoint rp = genTrain.getRoutePoints().get(1);

        assertEquals(Converter.convertStringToLocalTime(rpDto.getArrivalTime()), rp.getArrivalTime());
        assertEquals(Converter.convertStringToLocalTime(rpDto.getDepartTime()), rp.getDepartTime());
        assertEquals(rpDto.getDaysFromTrainDepartToArrivalHere(), rp.getDaysFromTrainDepartToArrivalHere());
        assertEquals(rpDto.getDaysFromTrainDepartToDepartFromHere(), rp.getDaysFromTrainDepartToDepartFromHere());
        assertEquals((Long) rpDto.getStationId(), rp.getStation().getId());
    }

    @Test
    @Transactional
    public void createGenericTrainTest() {

        GenericTrainDto genTrainDto = createTestGenTrainDto();
        Long newGenTrainId = genericTrainService.createGenericTrain(genTrainDto);
        GenericTrain genTrain = genericTrainDao.getGenericTrainById(newGenTrainId);
        assertNotNull(genTrain);
        checkEntityAndDtoForAccordance(genTrain, genTrainDto);
    }

    @Test
    @Transactional(readOnly = true)
    public void getGenericTrainDtoByIdTest() {

        GenericTrainDto unexistingTrain = genericTrainService.getGenericTrainDtoById(88888L);
        assertNull(unexistingTrain);

        GenericTrainDto genTrainDto = genericTrainService.getGenericTrainDtoById(1L); //from import_test_data.sql
        GenericTrain genTrain = genericTrainDao.getGenericTrainById(1L);
        checkEntityAndDtoForAccordance(genTrain, genTrainDto);
    }

    @Test
    @Transactional(readOnly = true)
    public void getAllGenericTrainDTOsTest() {
        List<GenericTrainDto> list = genericTrainService.getAllGenericTrainDTOs(); //USING SUBSELECT
        assertNotNull(list);
        assertEquals(2, list.size());
    }


    @Test
    public void shit() {
        System.out.println("YO!");
    }





//    @Test
//    @Transactional
//    @Rollback(false)
//    @Ignore
//    public void BeanValidationViolation() {
//
//        GenericTrainDto genTrainDto = createTestGenTrainDto();
//        Iterator<RoutePointDto> iterator = genTrainDto.getRoutePointDtoSet().iterator();
//        iterator.next();
//        iterator.remove();
//
//        genericTrainService.createGenericTrain(genTrainDto);
//
//        System.out.println("3232323");
//    }
//    @Test
//    @Ignore
//    @Transactional
//    @Rollback(value = false)
//    public void constraintViolationTest() {
//
//        GenericTrainDto genTrainDto = createTestGenTrainDto();
//        genericTrainService.createGenericTrain(genTrainDto);
//        assertThrows(RuntimeException.class, () -> genericTrainService.createGenericTrain(genTrainDto));
//
//    }
//
//
//
//    @BeforeClass
//    public static void beforeClass() {
//        System.out.println(" @BeforeClass is running ");
////        assertEquals(2, 3);
//    }
//
//    @Before
//    public void before() {
//        System.out.println(" @Before is running ");
////        assertEquals(2, 3);
//    }


}