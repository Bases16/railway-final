package edu.arf4.trains.railwayfinal.service;

import edu.arf4.trains.railwayfinal.dao.GenericTrainDao;
import edu.arf4.trains.railwayfinal.dao.StationDao;
import edu.arf4.trains.railwayfinal.dto.GenericTrainDto;
import edu.arf4.trains.railwayfinal.dto.RoutePointDto;
import edu.arf4.trains.railwayfinal.dto.ScheduleDto;
import edu.arf4.trains.railwayfinal.model.GenericTrain;
import edu.arf4.trains.railwayfinal.model.RoutePoint;
import edu.arf4.trains.railwayfinal.model.Schedule;
import edu.arf4.trains.railwayfinal.util.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
//@Transactional
public class GenericTrainService {

    @Autowired
    GenericTrainDao genericTrainDao;
    @Autowired
    StationDao stationDao;

    @Transactional
    public Long createGenericTrain(GenericTrainDto dto) {

        Schedule schedule = convertScheduleDtoToSchedule(dto.getSchedule());
        GenericTrain genericTrain = new GenericTrain(schedule);
        genericTrain.setNumber(dto.getNumber());
        genericTrain.setRoute(dto.getRoute());
        genericTrain.setNumOfPlazkartCars(dto.getNumOfPlazkartCars());
        genericTrain.setNumOfSeatsInPlazkartCar(dto.getNumOfSeatsInPlazkartCar());
        genericTrain.setNumOfCoopeCars(dto.getNumOfCoopeCars());
        genericTrain.setNumOfSeatsInCoopeCar(dto.getNumOfSeatsInCoopeCar());
        genericTrain.setNumOfSwCars(dto.getNumOfSwCars());
        genericTrain.setNumOfSeatsInSwCar(dto.getNumOfSeatsInSwcar());

        Set<RoutePoint> routePointSet = convertRoutePointDtoSetToRoutePointSet(dto.getRoutePointDtoSet(), genericTrain);
        genericTrain.setRoutePoints(routePointSet);

        return this.genericTrainDao.addGenericTrain(genericTrain);
    }

    private Schedule convertScheduleDtoToSchedule(ScheduleDto dto) {
        Schedule schedule = new Schedule();
        schedule.setWeekPeriodicity(dto.getWeekPeriodicity());
        schedule.setMonday(dto.getMonday());
        schedule.setTuesday(dto.getTuesday());
        schedule.setWednesday(dto.getWednesday());
        schedule.setThursday(dto.getThursday());
        schedule.setFriday(dto.getFriday());
        schedule.setSaturday(dto.getSaturday());
        schedule.setSunday(dto.getSunday());
        return schedule;
    }
    private Set<RoutePoint> convertRoutePointDtoSetToRoutePointSet(Set<RoutePointDto> dtoSet, GenericTrain genericTrain) {
        Set<RoutePoint> set = new HashSet<>();
        if (dtoSet != null) {
            for(RoutePointDto dto : dtoSet) { set.add(convertRoutePointDtoToRoutePoint(dto, genericTrain)); }
        }
        return set;
    }
//    @Transactional
    public RoutePoint convertRoutePointDtoToRoutePoint(RoutePointDto dto, GenericTrain genericTrain) {
        RoutePoint point = new RoutePoint();
        point.setStation(this.stationDao.getStationById(dto.getStationId(), true));
        point.setOrderOfStation(dto.getOrderOfStation());
        point.setDaysFromTrainDepartToArrivalHere(dto.getDaysFromTrainDepartToArrivalHere());
        point.setDaysFromTrainDepartToDepartFromHere(dto.getDaysFromTrainDepartToDepartFromHere());
        point.setDepartTime(Converter.convertStringToLocalTime(dto.getDepartTime()));
        point.setArrivalTime(Converter.convertStringToLocalTime(dto.getArrivalTime()));
        point.setGenericTrain(genericTrain);
        return point;
    }





    @Transactional(readOnly = true)
    public List<GenericTrainDto> getAllGenericTrains() {

        List<GenericTrain> genericTrains = this.genericTrainDao.getAllGenericTrains();

        if(genericTrains == null || genericTrains.isEmpty()) {
            return null;  // todo  ?????????????????????????????????
        }
        List<GenericTrainDto> dtoList = new ArrayList<>();
        for(GenericTrain gt : genericTrains) {
            GenericTrainDto dto = convertGenTrainToGenTrainDto(gt);
            dtoList.add(dto);
        }
        return dtoList;
    }

    private GenericTrainDto convertGenTrainToGenTrainDto(GenericTrain gt) {
        GenericTrainDto trainDto = new GenericTrainDto();
        trainDto.setNumber(gt.getNumber());
        trainDto.setRoute(gt.getRoute());
        trainDto.setNumOfPlazkartCars(gt.getNumOfPlazkartCars());
        trainDto.setNumOfSeatsInPlazkartCar(gt.getNumOfSeatsInPlazkartCar());
        trainDto.setNumOfCoopeCars(gt.getNumOfSeatsInCoopeCar());
        trainDto.setNumOfSeatsInCoopeCar(gt.getNumOfSeatsInCoopeCar());
        trainDto.setNumOfSwCars(gt.getNumOfSwCars());
        trainDto.setNumOfSeatsInSwcar(gt.getNumOfSeatsInCoopeCar());

        ScheduleDto scheduleDto = new ScheduleDto();
        Schedule schedule = gt.getSchedule();
        scheduleDto.setWeekPeriodicity(schedule.getWeekPeriodicity());
        scheduleDto.setMonday(schedule.getMonday());
        scheduleDto.setTuesday(schedule.getTuesday());
        scheduleDto.setWednesday(schedule.getWednesday());
        scheduleDto.setThursday(schedule.getThursday());
        scheduleDto.setFriday(schedule.getFriday());
        scheduleDto.setSaturday(schedule.getSaturday());
        scheduleDto.setSunday(schedule.getSunday());
        trainDto.setSchedule(scheduleDto);

        Set<RoutePointDto> rpDtoSet = new HashSet<>();
        for(RoutePoint rp : gt.getRoutePoints()) {
            RoutePointDto rpDto = new RoutePointDto();
            rpDto.setStationId(rp.getStation().getId());
            rpDto.setOrderOfStation(rp.getOrderOfStation());
            rpDto.setArrivalTime(Converter.convertLocalTimeToString(rp.getArrivalTime()));
            rpDto.setDepartTime(Converter.convertLocalTimeToString(rp.getDepartTime()));
            rpDto.setDaysFromTrainDepartToArrivalHere(rp.getDaysFromTrainDepartToArrivalHere());
            rpDto.setDaysFromTrainDepartToDepartFromHere(rp.getDaysFromTrainDepartToDepartFromHere());
            rpDtoSet.add(rpDto);
        }
        trainDto.setRoutePointDtoSet(rpDtoSet);

        return trainDto;
    }

}
