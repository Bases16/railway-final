package edu.arf4.trains.railwayfinal.service;

import edu.arf4.trains.railwayfinal.dao.GenericTrainDao;
import edu.arf4.trains.railwayfinal.dao.TrainDao;
import edu.arf4.trains.railwayfinal.dto.TrainDto;
import edu.arf4.trains.railwayfinal.model.GenericTrain;
import edu.arf4.trains.railwayfinal.model.RoutePoint;
import edu.arf4.trains.railwayfinal.model.Schedule;
import edu.arf4.trains.railwayfinal.model.SpecRoutePoint;
import edu.arf4.trains.railwayfinal.model.Train;
import edu.arf4.trains.railwayfinal.model.TrainCar;
import edu.arf4.trains.railwayfinal.model.TrainCarType;
import edu.arf4.trains.railwayfinal.util.Converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

//@Profile({"main", "alter"})
@Service
//@Transactional
public class TrainService {

    private final GenericTrainDao genericTrainDao;
    private final TrainDao trainDao;

    @Autowired
    public TrainService(GenericTrainDao genericTrainDao, TrainDao trainDao) {
        this.genericTrainDao = genericTrainDao;
        this.trainDao = trainDao;
    }


    /**
     *
     * @param startDate always must be monday
     * @param endDate   always must be later monday
     */
    @Transactional
    public void registerTrainByGivenDatesAndGenTrain(Long genTrainId, LocalDate startDate, LocalDate endDate) {

        GenericTrain genericTrain = this.genericTrainDao.getGenericTrainById(genTrainId);

        List<LocalDate> dateList = calcDepartDatesFromScheduleByDates(genericTrain.getSchedule(), startDate, endDate);

        if (!dateList.isEmpty()) {
            for(LocalDate date : dateList) {
                registerTrainOnDate(genericTrain, date);
            }

        } else throw new RuntimeException("This train doesn't go these days"); //todo runtime exc
    }




    private void registerTrainOnDate(GenericTrain genericTrain, LocalDate date) {

        Train train = new Train(genericTrain);
        train.setDepartDate(date);

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

    private int addTrainCarsOfSpecTypeInGivenTrain(int orderOfCar, Train train, TrainCarType type,
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

    private List<LocalDate> calcDepartDatesFromScheduleByDates(Schedule schedule, LocalDate startDate, LocalDate endDate) {

        List<LocalDate> resultList = new ArrayList<>();
        int weekPeriodicity = schedule.getWeekPeriodicity();
        List<DayOfWeek> departDaysOfWeek = Converter.getDaysOfWeekFromSchedule(schedule);

        long period = ChronoUnit.DAYS.between(startDate, endDate);

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
        LocalDateTime en = LocalDateTime.of(end, LocalTime.MIN);

        List<SpecRoutePoint> srpList = this.trainDao.getSrpListByStationId(stationId, st, en);

        if(srpList == null || srpList.isEmpty()) {
            return null;                            // todo  ???????
        }
        List<TrainDto> trainDtoList = new ArrayList<>();
        for(SpecRoutePoint srp : srpList) {
            TrainDto dto = new TrainDto();
            dto.setId(srp.getTrain().getId());
            dto.setLocalSrcDepartDateTime(Converter.convertLocalDateTimeToString(srp.getDepartDatetime()));
            dto.setLocalSrcArrivalDateTime(Converter.convertLocalDateTimeToString(srp.getArrivalDatetime()));
            dto.setNumber(srp.getRoutePoint().getGenericTrain().getNumber());
            dto.setGlobalRoute(srp.getRoutePoint().getGenericTrain().getRoute());
            trainDtoList.add(dto);
        }
        return trainDtoList;
    }


    @Transactional(readOnly = true)
    public List<TrainDto> getTrainDtoListBy2StationsAndDateRange(Long stationFromId, Long stationToId,
                                                                       LocalDate start, LocalDate end) {
        LocalDateTime st = LocalDateTime.of(start, LocalTime.MIN);
        LocalDateTime en = LocalDateTime.of(end, LocalTime.MIN);

        // could optimize search not looking for srp among "arriving at" stations
        List<SpecRoutePoint> srpList = this.trainDao.getSrpListByStationId(stationFromId, st, en);

        if(srpList == null || srpList.isEmpty()) {
            return null;                            // todo  ???????
        }
        List<TrainDto> trainDtoList = new ArrayList<>();

        for(SpecRoutePoint srp : srpList) {
            RoutePoint thisRP = srp.getRoutePoint();
            Set<RoutePoint> rpList = thisRP.getGenericTrain().getRoutePoints();// batch and subselect executes here

            for (RoutePoint rp : rpList) {
                if (rp.getStation().getId().equals(stationToId) && rp.getOrderOfStation() > thisRP.getOrderOfStation()) {
                    TrainDto dto = new TrainDto();
                    dto.setId(srp.getTrain().getId());
                    dto.setGlobalRoute(rp.getGenericTrain().getRoute());
                    dto.setNumber(rp.getGenericTrain().getNumber());
                    dto.setLocalSrcDepartDateTime(Converter.convertLocalDateTimeToString(srp.getDepartDatetime()));
                    dto.setLocalSrcArrivalDateTime(Converter.convertLocalDateTimeToString(srp.getArrivalDatetime()));

                    LocalDate d = srp.getDepartDatetime().toLocalDate();
                    LocalDate requiredDate = d.plusDays(rp.getDaysFromTrainDepartToArrivalHere() -
                            thisRP.getDaysFromTrainDepartToDepartFromHere());
                    dto.setLocalDstArrivalDateTime(Converter.convertLocalDateTimeToString(
                            LocalDateTime.of(requiredDate, rp.getArrivalTime())));

                    trainDtoList.add(dto);
                    break;
                }
            }
        }
        return trainDtoList;
    }

}




























