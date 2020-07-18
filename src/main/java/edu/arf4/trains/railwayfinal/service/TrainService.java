package edu.arf4.trains.railwayfinal.service;

import edu.arf4.trains.railwayfinal.dao.GenericTrainDao;
import edu.arf4.trains.railwayfinal.dao.TrainDao;
import edu.arf4.trains.railwayfinal.model.GenericTrain;
import edu.arf4.trains.railwayfinal.model.Schedule;
import edu.arf4.trains.railwayfinal.model.Train;
import edu.arf4.trains.railwayfinal.model.TrainCar;
import edu.arf4.trains.railwayfinal.util.Converter;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class TrainService {

    @Autowired
    GenericTrainDao genericTrainDao;
    @Autowired
    TrainDao trainDao;


    /**
     *
     * @param startDate always must be monday
     * @param endDate   always must be sunday
     */
    public void registerTrainByGivenDatesAndGenTrain(Long genTrainId, LocalDate startDate, LocalDate endDate) {

        Schedule schedule = this.genericTrainDao.getScheduleByGenTrainId(genTrainId);

        List<LocalDate> dateList = calcDepartDatesFromScheduleByDates(schedule, startDate, endDate);

        GenericTrain genericTrain = this.genericTrainDao.getGenericTrainById(genTrainId);

        if (!dateList.isEmpty()) {
            for(LocalDate date : dateList) {
                registerTrainOnDate(genericTrain, date);
            }
        } else throw new RuntimeException("This train doesn't go these days");
    }



    private void registerTrainOnDate(GenericTrain genericTrain, LocalDate date) {

            Train train = new Train();
            train.setDepartDate(date);

            Set<TrainCar> trainCarSet = train.getTrainCars();



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
