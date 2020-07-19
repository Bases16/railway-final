import edu.arf4.trains.railwayfinal.model.Schedule;
import edu.arf4.trains.railwayfinal.model.Train;
import edu.arf4.trains.railwayfinal.model.TrainCarType;
import edu.arf4.trains.railwayfinal.service.TrainService;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SimpleTestsForMe {

    final TrainService trainService = new TrainService();

    @Test
    public void testCalcDepartDatesFromScheduleByDates() {

        Schedule schedule = new Schedule();
        schedule.setMonday(true);
        schedule.setTuesday(false);
        schedule.setWednesday(false);
        schedule.setThursday(true);
        schedule.setFriday(false);
        schedule.setSaturday(false);
        schedule.setSunday(true);

        LocalDate startDate = LocalDate.of(2020, 7, 20);
        LocalDate endDate = LocalDate.of(2020, 8, 30);

        schedule.setWeekPeriodicity(3);
        List<LocalDate> list = trainService.calcDepartDatesFromScheduleByDates(schedule, startDate, endDate);
        assertEquals(list.size(), 6);

        schedule.setWeekPeriodicity(6);
        list = trainService.calcDepartDatesFromScheduleByDates(schedule, startDate, endDate);
        assertEquals(list.size(), 3);

        schedule.setWeekPeriodicity(5);
        list = trainService.calcDepartDatesFromScheduleByDates(schedule, startDate, endDate);
        assertEquals(list.size(), 6);

    }


    @Test
    public void testAddTrainCarsOfSpecTypeInTrain() {

        Train train = new Train();

        int orderOfCar = 1;

        orderOfCar = trainService.addTrainCarsOfSpecTypeInGivenTrain(orderOfCar, train, TrainCarType.PLATZKART,
                                                                 3, 6 );
        assertEquals(orderOfCar, 4);

        trainService.addTrainCarsOfSpecTypeInGivenTrain(orderOfCar, train, TrainCarType.COOPE,
                                                                 2, 4 );
        assertEquals(train.getTrainCars().size(), 5);

        train.getTrainCars().iterator().forEachRemaining( v -> System.out.println(v.getSeats().size() + " " + v.getType()
                                                         + " " + v.getOrderOfCar() ));
    }


    @Test
    public void testDatesAndContains() {
        LocalDate date1 = LocalDate.of(2020, 7, 15);
        LocalDate date2 = LocalDate.of(2020, 7, 17);
        LocalDate date3 = LocalDate.of(2020, 7, 17);

        assertNotSame(date2, date3);
        assertEquals(date2, date3);

        List<LocalDate> localDateList = new ArrayList<>();
        localDateList.add(date1);
        localDateList.add(date2);

        assertTrue(localDateList.contains(date3));
    }
}
