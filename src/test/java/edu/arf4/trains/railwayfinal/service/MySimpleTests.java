package edu.arf4.trains.railwayfinal.service;

import org.junit.Ignore;
import org.junit.Test;

import java.time.LocalDate;
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

        String route = "moscow - vrn";
        String[] a = route.split(" ");
        System.out.println(a[0] + " " + a[2]);


        List<String> list = new ArrayList<>();

    }






}




