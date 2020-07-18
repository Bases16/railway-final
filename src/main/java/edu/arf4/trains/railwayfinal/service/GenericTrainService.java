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

import java.util.HashSet;
import java.util.Set;

@Service
public class GenericTrainService {

    @Autowired
    GenericTrainDao genericTrainDao;
    @Autowired
    StationDao stationDao;

    public Long createGenericTrain(GenericTrainDto genericTrainDto) {

        Schedule schedule = convertDtoToSchedule(genericTrainDto.getSchedule());
        GenericTrain genericTrain = new GenericTrain(schedule);
        genericTrain.setNumber(genericTrainDto.getNumber());
        Set<RoutePoint> routePointSet = convertDtoSetToRoutePointSet(genericTrainDto.getRoutePointDtoSet(), genericTrain);
        genericTrain.setRoutePoints(routePointSet);

        return this.genericTrainDao.addGenericTrain(genericTrain);
    }



    private Schedule convertDtoToSchedule(ScheduleDto dto) {
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
    private Set<RoutePoint> convertDtoSetToRoutePointSet(Set<RoutePointDto> dtoSet, GenericTrain genericTrain) {
        Set<RoutePoint> set = new HashSet<>();
        if (dtoSet != null) {
            for(RoutePointDto dto : dtoSet) { set.add(convertDtoToRoutePoint(dto, genericTrain)); }
        }
        return set;
    }
    private RoutePoint convertDtoToRoutePoint(RoutePointDto dto, GenericTrain genericTrain) {
        RoutePoint point = new RoutePoint();
        point.setStation(this.stationDao.getStationProxyById(dto.getStationId()));
        point.setOrderOfStation(dto.getOrderOfStation());
        point.setDaysFromTrainDepartToArrivalHere(dto.getDaysFromTrainDepartToArrivalHere());
        point.setDaysFromTrainDepartToDepartFromHere(dto.getDaysFromTrainDepartToDepartFromHere());
        point.setDepartTime(dto.getDepartTime() == null ? null : Converter.convertStringToLocalTime(dto.getDepartTime()));
        point.setArrivalTime(dto.getArrivalTime() == null ? null : Converter.convertStringToLocalTime(dto.getArrivalTime()));
        point.setGenericTrain(genericTrain);
        return point;
    }


}
