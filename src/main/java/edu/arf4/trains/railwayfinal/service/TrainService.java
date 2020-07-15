package edu.arf4.trains.railwayfinal.service;

import edu.arf4.trains.railwayfinal.dao.GenericTrainDao;
import edu.arf4.trains.railwayfinal.model.GenericTrain;
import edu.arf4.trains.railwayfinal.model.Schedule;
import edu.arf4.trains.railwayfinal.util.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
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
    public List<LocalDate> calcDepartDatesFromScheduleByDates(Schedule schedule, LocalDate startDate, LocalDate endDate) {

        List<LocalDate> resultList = new ArrayList<>();

        int weekPeriodicity = schedule.getWeekPeriodicity();

        List<DayOfWeek> departDaysOfWeek = Converter.getDaysOfWeekFromSchedule(schedule);

        int period = Period.between(startDate, endDate).getDays() + 1;


        for (int i = 1; i <= period; i++) {

            LocalDate date = startDate.plusDays(i - 1);

            DayOfWeek dayOfWeek = DayOfWeek.from(date);

            int weekOrder = -1;
            float rawWeekOrder = (float) i  /  7F;

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


    protected void registerTrain(LocalDate date, GenericTrain gt) {
    }


}
