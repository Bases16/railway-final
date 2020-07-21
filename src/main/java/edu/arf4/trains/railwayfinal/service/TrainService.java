package edu.arf4.trains.railwayfinal.service;

import edu.arf4.trains.railwayfinal.dao.GenericTrainDao;
import edu.arf4.trains.railwayfinal.dao.TrainDao;
import edu.arf4.trains.railwayfinal.dto.TrainDto;
import edu.arf4.trains.railwayfinal.model.Example;
import edu.arf4.trains.railwayfinal.model.GenericTrain;
import edu.arf4.trains.railwayfinal.model.RoutePoint;
import edu.arf4.trains.railwayfinal.model.Schedule;
import edu.arf4.trains.railwayfinal.model.SpecRoutePoint;
import edu.arf4.trains.railwayfinal.model.Train;
import edu.arf4.trains.railwayfinal.model.TrainCar;
import edu.arf4.trains.railwayfinal.model.TrainCarType;
import edu.arf4.trains.railwayfinal.util.Converter;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
//@Transactional
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
    @Transactional
    public void registerTrainByGivenDatesAndGenTrain(Long genTrainId, LocalDate startDate, LocalDate endDate) {

        GenericTrain genericTrain = this.genericTrainDao.getGenericTrainById(genTrainId);

        List<LocalDate> dateList = calcDepartDatesFromScheduleByDates(genericTrain.getSchedule(), startDate, endDate);

        if (!dateList.isEmpty()) {
            for(LocalDate date : dateList) {
                registerTrainOnDate(genericTrain, date);
            }

            this.trainDao.addExample(new Example(9));

        } else throw new RuntimeException("This train doesn't go these days"); //todo runtime exc
    }

    public void registerTrainOnDate(GenericTrain genericTrain, LocalDate date) {

        Train train = new Train();
        train.setDepartDate(date);

        Set<TrainCar> trainCarSet = train.getTrainCars();

        int orderOfCar = 1;
        int numOfPlazkartCars = genericTrain.getNumOfPlazkartCars();
        int numOfSeatsInPlazkartCar = genericTrain.getNumOfSeatsInPlazkartCar();
        int numOfCoopeCars = genericTrain.getNumOfCoopeCars();
        int numOfSeatsInCoopeCar = genericTrain.getNumOfSeatsInCoopeCar();
        int numOfSwCars = genericTrain.getNumOfSwCars();
        int numOfSeatsInSwCar = genericTrain.getNumOfSeatsInSwCar();

        orderOfCar = addTrainCarsOfSpecTypeInGivenTrain(orderOfCar, train, TrainCarType.PLATZKART,
                                                                     numOfPlazkartCars, numOfSeatsInPlazkartCar  );
        orderOfCar = addTrainCarsOfSpecTypeInGivenTrain(orderOfCar, train, TrainCarType.COOPE,
                                                                     numOfCoopeCars, numOfSeatsInCoopeCar  );
        addTrainCarsOfSpecTypeInGivenTrain(orderOfCar, train, TrainCarType.SW, numOfSwCars, numOfSeatsInSwCar  );

        Set<SpecRoutePoint> specRoutePoints = train.getSpecRoutePoints();


        for (RoutePoint rp : genericTrain.getRoutePoints()) {

            SpecRoutePoint specRP = new SpecRoutePoint(train);
            specRP.setRoutePoint(rp);
            int numberOfSeatsInTrain = (numOfPlazkartCars * numOfSeatsInPlazkartCar) + (numOfCoopeCars * numOfSeatsInCoopeCar)
                                                                 + (numOfSwCars * numOfSeatsInSwCar);
            specRP.setTicketsLeft(numberOfSeatsInTrain);

            LocalTime departTime = rp.getDepartTime();
            LocalTime arrivalTime = rp.getArrivalTime();
            Integer daysFromTrainDepartToDepartFromHere = rp.getDaysFromTrainDepartToDepartFromHere();
            Integer daysFromTrainDepartToArrivalHere = rp.getDaysFromTrainDepartToArrivalHere();

            if(departTime != null) {
                LocalDate departDate = date.plusDays(daysFromTrainDepartToDepartFromHere);
                specRP.setDepartDatetime(LocalDateTime.of(departDate, departTime));
            }
            if(arrivalTime != null) {
                LocalDate arrivalDate = date.plusDays(daysFromTrainDepartToArrivalHere);
                specRP.setArrivalDatetime(LocalDateTime.of(arrivalDate, arrivalTime));
            }
            specRoutePoints.add(specRP);
        }
        this.trainDao.addTrain(train);
    }

    public int addTrainCarsOfSpecTypeInGivenTrain(int orderOfCar, Train train, TrainCarType type,
                                                  int numOfTypeCars, int numOfSeatsInTypeCar)      {

        Set<TrainCar> trainCarSet = train.getTrainCars();
        for(int i = 1; i <= numOfTypeCars; i++) {
            TrainCar trainCar = new TrainCar(orderOfCar, type);
            Map<Integer, Boolean> seats = trainCar.getSeats();

            for(int j = 1; j <= numOfSeatsInTypeCar; j++) {
                seats.put(j, false);
            }
            trainCar.setSeats(seats);
            trainCar.setTrain(train);
            trainCarSet.add(trainCar);
            orderOfCar++;
        }
        return orderOfCar;
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



    @Transactional(readOnly = true)
    public List<TrainDto> getTrainDtoListByStation(Long stationId, LocalDate start, LocalDate end) {

        LocalDateTime st = LocalDateTime.of(start, LocalTime.MIN);
        LocalDateTime en = LocalDateTime.of(end, LocalTime.MIN).plusDays(1);

        List<SpecRoutePoint> srpList = this.trainDao.getSrpListByStationId(stationId, st, en);

        if(srpList == null || srpList.isEmpty()) {
            return null;                            // todo  ???????
        }

        List<TrainDto> trainDtoList = new ArrayList<>();

        for(SpecRoutePoint srp : srpList) {

            TrainDto dto = new TrainDto();
            dto.setDepartDateTime(Converter.convertLocalDateTimeToString(srp.getDepartDatetime()));
            dto.setArrivalDateTime(Converter.convertLocalDateTimeToString(srp.getArrivalDatetime()));
            dto.setNumber(srp.getRoutePoint().getGenericTrain().getNumber());

            // todo      route   |   optimize number

            trainDtoList.add(dto);
        }

        return trainDtoList;
    }
}




























