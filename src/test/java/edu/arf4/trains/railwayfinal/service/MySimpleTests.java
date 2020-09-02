package edu.arf4.trains.railwayfinal.service;

import edu.arf4.trains.railwayfinal.config.DatabaseConfig;
import edu.arf4.trains.railwayfinal.model.Schedule;
import edu.arf4.trains.railwayfinal.model.Train;
import edu.arf4.trains.railwayfinal.model.TrainCarType;
import edu.arf4.trains.railwayfinal.service.TrainService;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;


public class MySimpleTests {





    @Test
    @Ignore
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




    @Ignore
    @Test
    public void WTF() {

        LocalDate dateBefore = LocalDate.of(2019, Month.FEBRUARY, 27);
        LocalDate dateAfter = LocalDate.of(2020, Month.MARCH, 2);
        long noOfDaysBetween = ChronoUnit.DAYS.between(dateBefore, dateAfter);
        System.out.println(noOfDaysBetween);

//        int y = 1/0;

    }






}




