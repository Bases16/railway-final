package edu.arf4.trains.railwayfinal.service;

import edu.arf4.trains.railwayfinal.dao.GenericTrainDao;
import edu.arf4.trains.railwayfinal.dao.StationDao;
import edu.arf4.trains.railwayfinal.dao.TrainDao;
import edu.arf4.trains.railwayfinal.dto.TrainDto;
import edu.arf4.trains.railwayfinal.model.GenericTrain;
import edu.arf4.trains.railwayfinal.model.RoutePoint;
import edu.arf4.trains.railwayfinal.model.Schedule;
import edu.arf4.trains.railwayfinal.model.SeatsStateAtPoint;
import edu.arf4.trains.railwayfinal.model.SpecRoutePoint;
import edu.arf4.trains.railwayfinal.model.Station;
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

//@Profile({"main", "alter"})
@Service
//@Transactional
public class TrainService {

    private final GenericTrainDao genericTrainDao;
    private final TrainDao trainDao;
    private final StationDao stationDao;

    @Autowired
    public TrainService(GenericTrainDao genericTrainDao, TrainDao trainDao, StationDao stationDao) {
        this.genericTrainDao = genericTrainDao;
        this.trainDao = trainDao;
        this.stationDao = stationDao;
        System.out.println(this.getClass().getSimpleName() + " WAS CREATED");
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

        int numOfPlazkartCars = genericTrain.getNumOfPlazkartCars();
        int numOfSeatsInPlazkartCar = genericTrain.getNumOfSeatsInPlazkartCar();
        int numOfCoopeCars = genericTrain.getNumOfCoopeCars();
        int numOfSeatsInCoopeCar = genericTrain.getNumOfSeatsInCoopeCar();
        int numOfSwCars = genericTrain.getNumOfSwCars();
        int numOfSeatsInSwCar = genericTrain.getNumOfSeatsInSwCar();

        int numberOfRoutePoints = genericTrain.getRoutePoints().size();

        addTrainCarsOfSpecTypeInGivenTrain(
                train, TrainCarType.PLAZKART, numOfPlazkartCars, numOfSeatsInPlazkartCar, numberOfRoutePoints
        );
        addTrainCarsOfSpecTypeInGivenTrain(
                train, TrainCarType.COOPE, numOfCoopeCars, numOfSeatsInCoopeCar, numberOfRoutePoints
        );
        addTrainCarsOfSpecTypeInGivenTrain(
                train, TrainCarType.SW, numOfSwCars, numOfSeatsInSwCar, numberOfRoutePoints
        );

        List<SpecRoutePoint> specRoutePoints = train.getSpecRoutePoints();

        for (RoutePoint rp : genericTrain.getRoutePoints()) {

            SpecRoutePoint specRP = new SpecRoutePoint(train);
            specRP.setRoutePoint(rp);

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

    private void addTrainCarsOfSpecTypeInGivenTrain(Train train, TrainCarType type,
                                                   int numOfTypeCars, int numOfSeatsInTypeCar, int numberOfRoutePoints) {

        List<TrainCar> trainCarList = train.getTrainCars();
        for(int i = 1; i <= numOfTypeCars; i++) {

            TrainCar trainCar = new TrainCar(type);
            List<SeatsStateAtPoint> seatsAtPoints = trainCar.getSeatsStateAtCar();

            for (int k = 1; k < numberOfRoutePoints; k++) {
                SeatsStateAtPoint seatsAtPoint = new SeatsStateAtPoint(numOfSeatsInTypeCar);
                seatsAtPoint.setTrainCar(trainCar);
                seatsAtPoints.add(seatsAtPoint);
            }
            trainCar.setTrain(train);
            trainCarList.add(trainCar);
        }
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

        List<SpecRoutePoint> srpList = this.trainDao.getSrpListByStationId(stationId, false, st, en);

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

        List<SpecRoutePoint> srpList = this.trainDao.getSrpListByStationId(stationFromId, true, st, en);

        if(srpList == null || srpList.isEmpty()) {
            return null;                            // todo  ???????
        }

        //STATIONS WILL NOT BE NULL 'CAUSE THEY ARE GET FROM VALID IDs
        Station from = this.stationDao.getStationById(stationFromId, false);
        Station to = this.stationDao.getStationById(stationToId, false);

        List<TrainDto> trainDtoList = new ArrayList<>();

        for(SpecRoutePoint srp : srpList) {
            RoutePoint rpFrom = srp.getRoutePoint();
            List<RoutePoint> rpList = rpFrom.getGenericTrain().getRoutePoints(); // batch('cause of JOIN FETCH in query)
                                                                                 // and subselect executes here
            for (int i = rpList.size() - 1;  ; i--) {   //start from the end to find stationTo faster

                RoutePoint rp = rpList.get(i);
                if (rp.equals(rpFrom)) break;

                if (rp.getStation().getId().equals(stationToId)) {

                    TrainDto dto = new TrainDto();

                    int indexStationFrom = rpList.indexOf(rpFrom);
                    setTicketsLeft(dto, srp.getTrain().getTrainCars(), indexStationFrom, i);

                    dto.setId(srp.getTrain().getId());
                    dto.setGlobalRoute(rp.getGenericTrain().getRoute());
                    dto.setNumber(rp.getGenericTrain().getNumber());
                    dto.setLocalSrcDepartDateTime(Converter.convertLocalDateTimeToString(srp.getDepartDatetime()));
                    dto.setLocalSrcArrivalDateTime(Converter.convertLocalDateTimeToString(srp.getArrivalDatetime()));
                    dto.setLocalRoute(from.getName() + " - " + to.getName());

                    LocalDate d = srp.getDepartDatetime().toLocalDate();
                    LocalDate requiredDate = d.plusDays(rp.getDaysFromTrainDepartToArrivalHere() -
                            rpFrom.getDaysFromTrainDepartToDepartFromHere());
                    dto.setLocalDstArrivalDateTime(Converter.convertLocalDateTimeToString(
                            LocalDateTime.of(requiredDate, rp.getArrivalTime())));

                    trainDtoList.add(dto);
                    break;
                }
            }
        }
        return trainDtoList;
    }

    private void setTicketsLeft(TrainDto dto, List<TrainCar> trainCars, int indStationFrom, int indStationTo) {

        int plazkartTicketsLeft = 0, coopeTicketsLeft = 0, swTicketsLeft = 0;

        for (TrainCar car : trainCars) {

            TrainCarType currentCarType = car.getType();
            List<SeatsStateAtPoint> seatsAtPoint = car.getSeatsStateAtCar();
            int commonCounter = Integer.MAX_VALUE;

            for (int i = indStationFrom; i < indStationTo; i++) {
                List<Boolean> seatStates = seatsAtPoint.get(i).getSeatStates();
                int counter = 0;
                for (Boolean seat : seatStates) {
                    if (!seat) counter++;
                }
                if (counter < commonCounter) commonCounter = counter;
            }
            if (currentCarType == TrainCarType.PLAZKART) plazkartTicketsLeft += commonCounter;
            if (currentCarType == TrainCarType.COOPE)     coopeTicketsLeft += commonCounter;
            if (currentCarType == TrainCarType.SW)        swTicketsLeft += commonCounter;
        }
        dto.setPlazkartTicketsLeft(plazkartTicketsLeft);
        dto.setCoopeTicketsLeft(coopeTicketsLeft);
        dto.setSwTicketsLeft(swTicketsLeft);
    }




}









