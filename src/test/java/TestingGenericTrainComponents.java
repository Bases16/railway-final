import edu.arf4.trains.railwayfinal.config.DatabaseConfig;
import edu.arf4.trains.railwayfinal.dao.GenericTrainDao;
import edu.arf4.trains.railwayfinal.dto.GenericTrainDto;
import edu.arf4.trains.railwayfinal.dto.RoutePointDto;
import edu.arf4.trains.railwayfinal.dto.ScheduleDto;
import edu.arf4.trains.railwayfinal.model.GenericTrain;
import edu.arf4.trains.railwayfinal.service.GenericTrainService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DatabaseConfig.class)
public class TestingGenericTrainComponents {

    @Autowired
    GenericTrainService genericTrainService;
    @Autowired
    GenericTrainDao genericTrainDao;


    final String RIGHT_GT_NUMBER = "777-AYE";

    @Test
    public void testingGenericTrainService() {

        GenericTrainDto genericTrainDto = new GenericTrainDto();

        genericTrainDto.setNumber(RIGHT_GT_NUMBER);

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
        pointDto1.setDaysFromTrainDepartToArrivalHere(0);

        pointDto2.setStationId(14); //Astana
        pointDto2.setOrderOfStation(2);
        pointDto2.setDepartTime(null);
        pointDto2.setArrivalTime("15:45");
        pointDto2.setDaysFromTrainDepartToDepartFromHere(2);
        pointDto2.setDaysFromTrainDepartToArrivalHere(2);

        routePointDtoSet.add(pointDto1);
        routePointDtoSet.add(pointDto2);

//        genericTrainDto.setRoutePointDtoSet(routePointDtoSet);


        genericTrainService.createGenericTrain(genericTrainDto);

        GenericTrain genericTrain = genericTrainDao.getGenericTrainByNumber(RIGHT_GT_NUMBER);

        assertNotNull(genericTrain);

//        assertEquals(genericTrain.getRoutePoints().size(), 2);

    }





}
