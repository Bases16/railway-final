package edu.arf4.trains.railwayfinal.service;

import edu.arf4.trains.railwayfinal.dao.GenericTrainDao;
import edu.arf4.trains.railwayfinal.model.Schedule;
import edu.arf4.trains.railwayfinal.util.Converter;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TrainService {

    @Autowired
    GenericTrainDao genericTrainDao;


    /**
     *
     *
     * @param startDate always must be monday
     * @param endDate   always must be sunday
     */
    public void registerTrainByGivenDatesAndGenTrain(Long genTrainId, LocalDate startDate, LocalDate endDate) {

        // need only schedule here
        // - we can load just a schedule from DB by Query creating
        // - we can load a GenericTrain from DB

        List<LocalDate> dateList = calcDepartDatesFromScheduleByDates(new Schedule(), startDate, endDate);

        if (!dateList.isEmpty()) {
                registerTrainOnDates(genTrainId, dateList);
        } else throw new RuntimeException("This train doesn't go these days");
    }



    private void registerTrainOnDates(Long genTrainId, List<LocalDate> dateList) {




        for(LocalDate date : dateList) {



        }



    }


    /**
     *
     * @param startDate always must be monday
     * @param endDate   always must be sunday
     */
    public List<LocalDate> calcDepartDatesFromScheduleByDates(Schedule schedule, LocalDate startDate, LocalDate endDate) {

        List<LocalDate> resultList = new ArrayList<>();
        int weekPeriodicity = schedule.getWeekPeriodicity();
        List<DayOfWeek> departDaysOfWeek = Converter.getDaysOfWeekFromSchedule(schedule);

        DateTime jodaStartDate = Converter.convertLocalDateToJodaDateTime(startDate);
        DateTime jodaEndDate = Converter.convertLocalDateToJodaDateTime(endDate);
        int period = Days.daysBetween(jodaStartDate, jodaEndDate).getDays() + 1;

        for (int i = 1; i <= period; i++) {
            LocalDate date = startDate.plusDays(i - 1);

            DayOfWeek dayOfWeek = DayOfWeek.from(date);

            int weekOrder;
            float rawWeekOrder = (float) i  /  7;

            if(rawWeekOrder % 1 == 0) {
                weekOrder = (int) (rawWeekOrder - 1);
            } else {
                weekOrder = (int) rawWeekOrder;
            }
            if(weekOrder % weekPeriodicity != 0) {
                i += 6;
                continue;
            }
            if(departDaysOfWeek.contains(dayOfWeek)) {
                resultList.add(date);
            }
        }
        return resultList;
    }


}
