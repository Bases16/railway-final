import edu.arf4.trains.railwayfinal.model.Schedule;
import edu.arf4.trains.railwayfinal.service.TrainService;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SimpleTestsForMe {

    private TrainService trainService = new TrainService();

    @Test
    public void testCalcDepartDatesFromScheduleByDates() {

        Schedule schedule = new Schedule();
        schedule.setWeekPeriodicity(3);
        schedule.setMonday(true);
        schedule.setTuesday(false);
        schedule.setWednesday(false);
        schedule.setThursday(true);
        schedule.setFriday(false);
        schedule.setSaturday(false);
        schedule.setSunday(true);


        LocalDate startDate = LocalDate.of(2020, 7, 20);
        LocalDate endDate = LocalDate.of(2020, 8, 30);

        List<LocalDate> list = trainService.calcDepartDatesFromScheduleByDates(schedule, startDate, endDate);

        assertEquals(list.size(), 18);
    }


    @Test
    public void testPeriod() {

        LocalDate startDate = LocalDate.of(2020, 2, 20);
        LocalDate endDate = LocalDate.of(2021, 2, 20);

        System.out.println(startDate.lengthOfMonth());
        System.out.println(endDate.lengthOfMonth());

    }




        @Test
    public void testWP() {

        float x = 8f / 7f;

        float reminder = 0f % 3;

        int y = (int) x;

//        if(x % 2 == 0)

        //int x = Double.valueOf( 8d / 7d).intValue();

        System.out.println(x);
        System.out.println(reminder);
        System.out.println(y);
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
