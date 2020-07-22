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

import java.util.HashSet;
import java.util.Set;

@Service
//@Transactional
public class GenericTrainService {

    @Autowired
    GenericTrainDao genericTrainDao;
    @Autowired
    StationDao stationDao;

    @Transactional
    public Long createGenericTrain(GenericTrainDto genericTrainDto) {

        Schedule schedule = convertScheduleDtoToSchedule(genericTrainDto.getSchedule());
        GenericTrain genericTrain = new GenericTrain(schedule);
        genericTrain.setNumber(genericTrainDto.getNumber());
        genericTrain.setRoute(genericTrainDto.getRoute());
        Set<RoutePoint> routePointSet = convertRoutePointDtoSetToRoutePointSet(genericTrainDto.getRoutePointDtoSet(), genericTrain);
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

}
