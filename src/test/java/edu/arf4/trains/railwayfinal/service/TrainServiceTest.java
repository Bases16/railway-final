package edu.arf4.trains.railwayfinal.service;

import edu.arf4.trains.railwayfinal.config.DatabaseConfig;
import edu.arf4.trains.railwayfinal.dao.TrainDao;
import edu.arf4.trains.railwayfinal.dto.TrainDto;
import edu.arf4.trains.railwayfinal.model.SpecRoutePoint;
import edu.arf4.trains.railwayfinal.model.Train;
import edu.arf4.trains.railwayfinal.model.TrainCar;
import edu.arf4.trains.railwayfinal.model.TrainCarType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

//@ContextConfiguration(classes = AlterDatabaseConfig.class)
@ContextConfiguration(classes = DatabaseConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class TrainServiceTest {

    @Autowired
    private TrainService trainService;
    @Autowired
    private TrainDao trainDao;

    @Test
    @Transactional
    public void registerTrainByGivenDatesAndGenTrainTest() {

        //CASE 1
        LocalDate start = LocalDate.of(2023, 4, 3);
        LocalDate end = LocalDate.of(2023, 5, 1);

        trainService.registerTrainByGivenDatesAndGenTrain(1L, start, end);

        List<Train> list = trainDao.getTrainsByGenTrainIdAndDates(1L, start, end);

        assertNotNull(list);
        assertEquals(list.size(), 4);

        //CASE 2
        trainService.registerTrainByGivenDatesAndGenTrain(2L, start, end);

        list = trainDao.getTrainsByGenTrainIdAndDates(2L, start, end);

        assertNotNull(list);
        assertEquals(list.size(), 4);

        //CASE 3 - FULL CHECKING
        start = LocalDate.of(2023, 5, 8);
        end = LocalDate.of(2023, 5, 29);
        trainService.registerTrainByGivenDatesAndGenTrain(2L, start, end);

        list = trainDao.getTrainsByGenTrainIdAndDates(2L, start, end);

        assertNotNull(list);
        assertEquals(list.size(), 2);

        Train train = list.get(0);
        Set<TrainCar> cars = train.getTrainCars();
        Set<SpecRoutePoint> srPoints = train.getSpecRoutePoints();

        //TRAIN CHECKING
        assertEquals(train.getDepartDate(), start); // monday is true
        assertEquals(train.getGenericTrain().getId(), new Long(2));

        // TRAIN CARS CHECKING
        assertNotNull(cars);
        assertEquals(cars.size(), 3);

        TrainCar plazkart = null, coope = null, sw = null;

        for (TrainCar car : cars) {
            if (car.getType().equals(TrainCarType.PLATZKART)) plazkart = car;
            if (car.getType().equals(TrainCarType.COOPE)) coope = car;
            if (car.getType().equals(TrainCarType.SW)) sw = car;
        }
        assertNotNull(plazkart);
        assertNotNull(coope);
        assertNotNull(sw);

        assertEquals(plazkart.getOrderOfCar(), new Integer(1));
        assertEquals(coope.getOrderOfCar(), new Integer(2));
        assertEquals(sw.getOrderOfCar(), new Integer(3));

        assertEquals(plazkart.getSeats().size(), 3);
        assertEquals(coope.getSeats().size(), 2);
        assertEquals(sw.getSeats().size(), 1);

        assertEquals(plazkart.getSeats().get(3), false);
        assertEquals(coope.getSeats().get(2), false);
        assertEquals(sw.getSeats().get(1), false);

        //SRP CHECKING
        assertNotNull(srPoints);
        assertEquals(srPoints.size(), 4);

        SpecRoutePoint srp = null;
        for (SpecRoutePoint point : srPoints) {
            if (point.getRoutePoint().getOrderOfStation().equals(2)) {
                srp = point;
                break;
            }
        }
        assertNotNull(srp);
        assertEquals(srp.getTicketsLeft(), new Integer(6));
        assertEquals(srp.getArrivalDatetime(), LocalDateTime.of(2023,5,8,23,55));
        assertEquals(srp.getDepartDatetime(), LocalDateTime.of(2023,5,9,1,0));
    }

    @Test
    public void getTrainDtoListByStationTest() {

        final Long MYSHKIN_ID = 15L;
        final Long GENOA_ID = 19L;

        //CASE 1
        LocalDate start = LocalDate.of(2020, 12, 2);
        LocalDate end = LocalDate.of(2020, 12, 6);

        List<TrainDto> dtoListByStation = trainService.getTrainDtoListByStation(MYSHKIN_ID, start, end);
        assertNotNull(dtoListByStation);
        assertEquals(dtoListByStation.size(), 2);

        //CASE 2
        start = LocalDate.of(2020, 12, 1);
        end = LocalDate.of(2020, 12, 7);

        dtoListByStation = trainService.getTrainDtoListByStation(MYSHKIN_ID, start, end);
        assertNotNull(dtoListByStation);
        assertEquals(dtoListByStation.size(), 4);

        //CASE 3 - FULL CHECKING
        start = LocalDate.of(2020, 11, 30);
        end = LocalDate.of(2020, 12, 1);

        dtoListByStation = trainService.getTrainDtoListByStation(MYSHKIN_ID, start, end);
        assertNotNull(dtoListByStation);
        assertEquals(dtoListByStation.size(), 1);

        TrainDto dto = dtoListByStation.get(0);

        assertEquals(dto.getNumber(), "1488HH");
        assertEquals(dto.getGlobalRoute(), "Liski - Genoa");
        assertEquals(dto.getId(), new Long(3));
        assertEquals(dto.getLocalSrcArrivalDateTime(), "2020-11-30 at 23:55");
        assertEquals(dto.getLocalSrcDepartDateTime(), "2020-12-01 at 01:00");

        assertNull(dto.getLocalRoute());
        assertNull(dto.getLocalDstArrivalDateTime());

        //CASE 4 - FULL CHECKING
        start = LocalDate.of(2020, 12, 7);
        end = LocalDate.of(2020, 12, 8);

        dtoListByStation = trainService.getTrainDtoListByStation(GENOA_ID, start, end);
        assertNotNull(dtoListByStation);
        assertEquals(dtoListByStation.size(), 1);

        dto = dtoListByStation.get(0);

        assertEquals(dto.getNumber(), "1488HH");
        assertEquals(dto.getGlobalRoute(), "Liski - Genoa");
        assertEquals(dto.getId(), new Long(4));
        assertEquals(dto.getLocalSrcArrivalDateTime(), "2020-12-07 at 22:00");
        assertEquals(dto.getLocalSrcDepartDateTime(), null);

        assertNull(dto.getLocalRoute());
        assertNull(dto.getLocalDstArrivalDateTime());

        System.out.println(dto);
    }

    @Test
    public void getTrainDtoListBy2StationsAndDateRangeTest() {

        final Long MYSHKIN_ID = 15L;
        final Long ASTANA_ID = 14L;
        final Long MOSCOW_ID = 16L;
        final Long HONG_KONG_ID = 20L;

        //CASE 1
        LocalDate start = LocalDate.of(2019, 12, 12);
        LocalDate end = LocalDate.of(2021, 12, 13);

        List<TrainDto> dtoListByStation = trainService
                .getTrainDtoListBy2StationsAndDateRange(MYSHKIN_ID, ASTANA_ID, start, end);
        assertNotNull(dtoListByStation);
        assertEquals(dtoListByStation.size(), 2);

        //CASE 2
        start = LocalDate.of(2020, 12, 1);
        end = LocalDate.of(2020, 12, 3);

        dtoListByStation = trainService
                .getTrainDtoListBy2StationsAndDateRange(MOSCOW_ID, HONG_KONG_ID, start, end);
        assertNotNull(dtoListByStation);
        assertEquals(dtoListByStation.size(), 1);

        //CASE 3
        start = LocalDate.of(2020, 12, 5);
        end = LocalDate.of(2020, 12, 6);

        dtoListByStation = trainService
                .getTrainDtoListBy2StationsAndDateRange(ASTANA_ID, HONG_KONG_ID, start, end);
        assertNull(dtoListByStation);

        //CASE 4 - FULL CHECKING
        start = LocalDate.of(2020, 12, 3);
        end = LocalDate.of(2020, 12, 4);

        dtoListByStation = trainService
                .getTrainDtoListBy2StationsAndDateRange(ASTANA_ID, HONG_KONG_ID, start, end);
        assertNotNull(dtoListByStation);
        assertEquals(dtoListByStation.size(), 1);

        TrainDto dto = dtoListByStation.get(0);

        assertEquals(dto.getNumber(), "МШМ228");
        assertEquals(dto.getGlobalRoute(), "Moscow - Hong-Kong");
        assertEquals(dto.getId(), new Long(1));
        assertEquals(dto.getLocalSrcArrivalDateTime(), "2020-12-03 at 11:10");
        assertEquals(dto.getLocalSrcDepartDateTime(), "2020-12-03 at 11:58");
        assertEquals(dto.getLocalDstArrivalDateTime(), "2020-12-05 at 22:00");

        assertNull(dto.getLocalRoute());
    }


}