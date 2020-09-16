package edu.arf4.trains.railwayfinal.service;

import edu.arf4.trains.railwayfinal.config.JtaDatabaseConfig;
import edu.arf4.trains.railwayfinal.dao.GenericTrainDao;
import edu.arf4.trains.railwayfinal.dao.StationDao;
import edu.arf4.trains.railwayfinal.dao.TrainDao;
import edu.arf4.trains.railwayfinal.dto.TrainDto;
import edu.arf4.trains.railwayfinal.exceptions.AlreadyRegisteredException;
import edu.arf4.trains.railwayfinal.exceptions.NoTicketsLeftException;
import edu.arf4.trains.railwayfinal.dto.TicketDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;


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
    @Autowired
    private TicketService ticketService;





    @Test
    @Transactional
//    @Rollback(value = false)
//    @Ignore
    public void buyTicket() throws Exception {

        TrainDto dto = new TrainDto();
        dto.setId(1L);
        dto.setLocalRoute("Moscow - Hong-Kong");
        dto.setLocalSrcDepartDateTime("2020-12-01 at 15:30");
        dto.setLocalDstArrivalDateTime("2020-12-05 at 22:00");

        assertThrows( AlreadyRegisteredException.class,
                () -> ticketService.buyTicket(dto, 1L, "SW")
        );

        assertThrows( NoTicketsLeftException.class,
                () -> ticketService.buyTicket(dto, 7L, "SW")
        );

        assertThrows( NoTicketsLeftException.class,
                () -> ticketService.buyTicket(dto, 7L, "PLAZKART")
        );


        TicketDto ticketDto = ticketService.buyTicket(dto, 7L, "COOPE");

        assertNotNull(ticketDto);
        assertEquals(new Integer(2), ticketDto.getTrainCarNumber());
        assertEquals(new Integer(2), ticketDto.getSeatNumber());
        assertEquals("f_name7", ticketDto.getPassengerFirstName());
        assertEquals("l_name7", ticketDto.getPassengerLastName());
        assertEquals("2020-12-01 at 15:30", ticketDto.getDepartureDateTime());
        assertEquals("2020-12-05 at 22:00", ticketDto.getArrivalDateTime());
        assertEquals("Moscow", ticketDto.getStationFrom());
        assertEquals("Hong-Kong", ticketDto.getStationTo());

        assertThrows( NoTicketsLeftException.class,
                () -> ticketService.buyTicket(dto, 8L, "COOPE")
        );

        dto.setLocalRoute("Myshkin - Hong-Kong");
        dto.setLocalSrcDepartDateTime("2020-12-01 at 23:55");

        assertThrows( NoTicketsLeftException.class,
                () -> ticketService.buyTicket(dto, 8L, "SW")
        );

        ticketDto = ticketService.buyTicket(dto, 8L, "PLAZKART");

        assertNotNull(ticketDto);
        assertEquals(new Integer(1), ticketDto.getTrainCarNumber());
        assertEquals(new Integer(3), ticketDto.getSeatNumber());
        assertEquals("f_name8", ticketDto.getPassengerFirstName());
        assertEquals("l_name8", ticketDto.getPassengerLastName());
        assertEquals("2020-12-01 at 23:55", ticketDto.getDepartureDateTime());
        assertEquals("2020-12-05 at 22:00", ticketDto.getArrivalDateTime());
        assertEquals("Myshkin", ticketDto.getStationFrom());
        assertEquals("Hong-Kong", ticketDto.getStationTo());

    }




//    @Test
//    @Transactional
//    @Ignore
//    public void test() {  // THE SCOPE OF GUARANTEED IDENTITY
//
//        Train train = trainDao.getTrainById(1L);
//        Station station = stationDao.getStationByName("Moscow");
//
//        List<SpecRoutePoint> srPoints = train.getSpecRoutePoints();
//
//        for (SpecRoutePoint point : srPoints) {
//
//            System.out.println(point.getRoutePoint().getStation().getName() );
//
////            if (point.getRoutePoint().getStation() == station) {
////                System.out.println("point.getRoutePoint().getStation() == station");
////                if (point.getRoutePoint().getStation().equals(station)) {
////                    System.out.println("point.getRoutePoint().getStation() AND EQUALS TOO");
////                }
////            }
//        }
//        System.out.println("NEXT");
//
//        GenericTrain genericTrain = genericTrainDao.getGenericTrainById(2L);
//        List<RoutePoint> routePoints = genericTrain.getRoutePoints();
//        for (RoutePoint point : routePoints) {
//            System.out.println(point.getStation().getName());
//        }
//
//    }




































}






