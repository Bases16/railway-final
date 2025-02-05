package edu.arf4.trains.railwayfinal.service;

import edu.arf4.trains.railwayfinal.config.DatabaseConfig;
import edu.arf4.trains.railwayfinal.dao.TrainDao;
import edu.arf4.trains.railwayfinal.dto.TrainDto;
import edu.arf4.trains.railwayfinal.model.SpecRoutePoint;
import edu.arf4.trains.railwayfinal.model.Train;
import edu.arf4.trains.railwayfinal.model.TrainCar;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.*;

@ContextConfiguration(classes = DatabaseConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class TrainServiceTest {

    @Autowired
    private TrainService trainService;
    @Autowired
    private TrainDao trainDao;

    @Test
    @Transactional
//    @Rollback(value = false)
    public void registerTrainByGivenDatesAndGenTrainTest() {

        //CASE 1
        LocalDate start = LocalDate.of(2025, 4, 3);
        LocalDate end = LocalDate.of(2025, 5, 1);

        trainService.registerTrainByGivenDatesAndGenTrain(1L, start, end);

        List<Train> list = trainDao.getTrainsByGenTrainIdAndDates(1L, start, end);

        assertNotNull(list);
        assertEquals(4, list.size());

        //CASE 2
        trainService.registerTrainByGivenDatesAndGenTrain(2L, start, end);

        list = trainDao.getTrainsByGenTrainIdAndDates(2L, start, end);

        assertNotNull(list);
        assertEquals(4, list.size());

        //CASE 3 - FULL CHECKING
        start = LocalDate.of(2025, 5, 5);
        end = LocalDate.of(2025, 5, 26);
        trainService.registerTrainByGivenDatesAndGenTrain(2L, start, end);

        list = trainDao.getTrainsByGenTrainIdAndDates(2L, start, end);

        assertNotNull(list);
        assertEquals(2, list.size());

        Train train = list.get(0);
        List<TrainCar> cars = train.getTrainCars();

        //TRAIN CHECKING
        assertEquals(start, train.getDepartDate()); // monday is true
        assertEquals(new Long(2), train.getGenericTrain().getId());

        // TRAIN CARS CHECKING
        assertNotNull(cars);
        assertEquals(3, cars.size());

        TrainCar plazkart = cars.get(0);
        TrainCar coope = cars.get(1);
        TrainCar sw = cars.get(2);

        assertNotNull(plazkart);
        assertNotNull(coope);
        assertNotNull(sw);

        assertEquals(3, plazkart.getSeatsStateAtCar().size());
        assertEquals(3, coope.getSeatsStateAtCar().size());
        assertEquals(3, sw.getSeatsStateAtCar().size());

        assertEquals(3, plazkart.getSeatsStateAtCar().get(0).getSeatStates().size());
        assertEquals(2, coope.getSeatsStateAtCar().get(0).getSeatStates().size());
        assertEquals(1, sw.getSeatsStateAtCar().get(0).getSeatStates().size());

        assertEquals(false, plazkart.getSeatsStateAtCar().get(0).getSeatStates().get(2));
        assertEquals(false, coope.getSeatsStateAtCar().get(0).getSeatStates().get(1));
        assertEquals(false, sw.getSeatsStateAtCar().get(0).getSeatStates().get(0));

        final TrainCar swFinal = sw;
        assertThrows(IndexOutOfBoundsException.class, () -> swFinal.getSeatsStateAtCar().get(0).getSeatStates().get(1));


        //SRP CHECKING
        List<SpecRoutePoint> srPoints = train.getSpecRoutePoints();
        assertNotNull(srPoints);
        assertEquals(4, srPoints.size());

        SpecRoutePoint srp = null;
        int i = 1;
        for (SpecRoutePoint point : srPoints) {
            if (i == 2) {
                srp = point;
                break;
            }
            i++;
        }
        assertNotNull(srp);
        assertEquals(LocalDateTime.of(2025,5,5,23,55), srp.getArrivalDatetime());
        assertEquals(LocalDateTime.of(2025,5,6,1,0), srp.getDepartDatetime());
    }

    @Test
    @Transactional(readOnly = true)
    public void getTrainDtoListByStationTest() {

        final Long MYSHKIN_ID = 15L;
        final Long GENOA_ID = 19L;

        //CASE 1
        LocalDate start = LocalDate.of(2025, 12, 3);
        LocalDate end = LocalDate.of(2025, 12, 7);

        List<TrainDto> dtoListByStation = trainService.getTrainDtoListByStation(MYSHKIN_ID, start, end);
        assertNotNull(dtoListByStation);
        assertEquals(2, dtoListByStation.size());

        //CASE 2
        start = LocalDate.of(2025, 12, 2);
        end = LocalDate.of(2025, 12, 8);

        dtoListByStation = trainService.getTrainDtoListByStation(MYSHKIN_ID, start, end);
        assertNotNull(dtoListByStation);
        assertEquals(4, dtoListByStation.size());

        //CASE 3 - FULL CHECKING
        start = LocalDate.of(2025, 12, 1);
        end = LocalDate.of(2025, 12, 2);

        dtoListByStation = trainService.getTrainDtoListByStation(MYSHKIN_ID, start, end);
        assertNotNull(dtoListByStation);
        assertEquals(1, dtoListByStation.size());

        TrainDto dto = dtoListByStation.get(0);

        assertEquals(dto.getNumber(), "1488HH");
        assertEquals(dto.getGlobalRoute(), "Liski - Genoa");
        assertEquals(dto.getId(), new Long(3));
        assertEquals(dto.getLocalSrcArrivalDateTime(), "2025-12-01 at 23:55");
        assertEquals(dto.getLocalSrcDepartDateTime(), "2025-12-02 at 01:00");

        assertNull(dto.getLocalRoute());
        assertNull(dto.getLocalDstArrivalDateTime());

        //CASE 4 - FULL CHECKING
        start = LocalDate.of(2025, 12, 8);
        end = LocalDate.of(2025, 12, 9);

        dtoListByStation = trainService.getTrainDtoListByStation(GENOA_ID, start, end);
        assertNotNull(dtoListByStation);
        assertEquals(1, dtoListByStation.size());

        dto = dtoListByStation.get(0);

        assertEquals("1488HH", dto.getNumber());
        assertEquals("Liski - Genoa", dto.getGlobalRoute());
        assertEquals(new Long(4), dto.getId());
        assertEquals("2025-12-08 at 22:00", dto.getLocalSrcArrivalDateTime());
        assertNull(dto.getLocalSrcDepartDateTime()); // specific case

        assertNull(dto.getLocalRoute());                             //TODO  LOCAL ROUTE ???
        assertNull(dto.getLocalDstArrivalDateTime());
    }

    @Test
    @Transactional(readOnly = true)
    public void getTrainDtoListBy2StationsAndDateRangeTest() {

        final Long MYSHKIN_ID = 15L;
        final Long ASTANA_ID = 14L;
        final Long MOSCOW_ID = 16L;
        final Long HONG_KONG_ID = 20L;

        //CASE 1
        LocalDate start = LocalDate.of(2024, 12, 12);
        LocalDate end = LocalDate.of(2026, 12, 13);

        List<TrainDto> dtoListByStation = trainService
                .getTrainDtoListBy2StationsAndDateRange(MYSHKIN_ID, ASTANA_ID, start, end);
        assertNotNull(dtoListByStation);
        assertEquals(2, dtoListByStation.size());

        //CASE 2
        start = LocalDate.of(2025, 12, 6);
        end = LocalDate.of(2025, 12, 7);

        dtoListByStation = trainService
                .getTrainDtoListBy2StationsAndDateRange(ASTANA_ID, HONG_KONG_ID, start, end);
        assertNull(dtoListByStation);

        //CASE 3 - TICKETSLEFT CHECKING
        start = LocalDate.of(2025, 12, 2);
        end = LocalDate.of(2025, 12, 4);

        dtoListByStation = trainService
                .getTrainDtoListBy2StationsAndDateRange(MOSCOW_ID, HONG_KONG_ID, start, end);
        assertNotNull(dtoListByStation);
        assertEquals(1, dtoListByStation.size());

        TrainDto dto = dtoListByStation.get(0);

        assertEquals(new Integer(0), dto.getPlazkartTicketsLeft());
        assertEquals(new Integer(1), dto.getCoopeTicketsLeft());
        assertEquals(new Integer(0), dto.getSwTicketsLeft());

        //CASE 4 - FULL CHECKING
        start = LocalDate.of(2025, 12, 4);
        end = LocalDate.of(2025, 12, 5);

        dtoListByStation = trainService
                .getTrainDtoListBy2StationsAndDateRange(ASTANA_ID, HONG_KONG_ID, start, end);
        assertNotNull(dtoListByStation);
        assertEquals(1, dtoListByStation.size());

        dto = dtoListByStation.get(0);

        assertEquals("МШМ228", dto.getNumber());
        assertEquals("Moscow - Hong-Kong", dto.getGlobalRoute());
        assertEquals(new Long(1), dto.getId());
        assertEquals("2025-12-04 at 11:10", dto.getLocalSrcArrivalDateTime());
        assertEquals("2025-12-04 at 11:58", dto.getLocalSrcDepartDateTime());
        assertEquals("2025-12-06 at 22:00", dto.getLocalDstArrivalDateTime());
        assertEquals("Astana - Hong-Kong", dto.getLocalRoute());

        assertEquals(new Integer(2), dto.getPlazkartTicketsLeft());
        assertEquals(new Integer(1), dto.getCoopeTicketsLeft());
        assertEquals(new Integer(1), dto.getSwTicketsLeft());
    }


}
