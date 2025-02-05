package edu.arf4.trains.railwayfinal.service;

import edu.arf4.trains.railwayfinal.dao.GenericTrainDao;
import edu.arf4.trains.railwayfinal.dto.GenericTrainDto;
import edu.arf4.trains.railwayfinal.dto.RoutePointDto;
import edu.arf4.trains.railwayfinal.dto.ScheduleDto;
import edu.arf4.trains.railwayfinal.model.GenericTrain;
import edu.arf4.trains.railwayfinal.model.RoutePoint;
import edu.arf4.trains.railwayfinal.model.Schedule;
import edu.arf4.trains.railwayfinal.util.Converter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

//@Profile({"main","alter"})
@Service
//@Transactional
public class GenericTrainService {

    public static final Logger log = LoggerFactory.getLogger(GenericTrainService.class);

    private final GenericTrainDao genericTrainDao;
    private final StationService stationService;

    public GenericTrainService(GenericTrainDao genericTrainDao, StationService stationService) {
        log.debug("{} WAS CREATED", this.getClass());
        this.genericTrainDao = genericTrainDao;
        this.stationService = stationService;
    }


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

        List<RoutePoint> routePointList = convertRoutePointDtoListToRoutePointList(dto.getRoutePointDtoList(), genericTrain);
        genericTrain.setRoutePoints(routePointList);

        Long newGeTrainId;
        try {
            newGeTrainId = this.genericTrainDao.addGenericTrain(genericTrain); //TODO EXCEPTION?
        } catch (RuntimeException e) {
            throw e;
        }

        return newGeTrainId;
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

    private List<RoutePoint> convertRoutePointDtoListToRoutePointList(List<RoutePointDto> dtoList, GenericTrain genericTrain) {
        List<RoutePoint> list = new ArrayList<>();
        if (dtoList != null) {
            for(RoutePointDto dto : dtoList) { list.add(convertRoutePointDtoToRoutePoint(dto, genericTrain)); }
        }
        return list;
    }

    private RoutePoint convertRoutePointDtoToRoutePoint(RoutePointDto dto, GenericTrain genericTrain) {
        RoutePoint point = new RoutePoint();
        point.setStation(this.stationService.getStationById(dto.getStationId(), true));
        point.setDaysFromTrainDepartToArrivalHere(dto.getDaysFromTrainDepartToArrivalHere());
        point.setDaysFromTrainDepartToDepartFromHere(dto.getDaysFromTrainDepartToDepartFromHere());
        point.setDepartTime(Converter.convertStringToLocalTime(dto.getDepartTime()));
        point.setArrivalTime(Converter.convertStringToLocalTime(dto.getArrivalTime()));
//        point.setGenericTrain(genericTrain);
        return point;
    }

    // this propagation 'cause not so frequently and important operations
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS) //todo do we need SUPPORTS here?
    public GenericTrainDto getGenericTrainDtoById(Long id) {
        GenericTrain genericTrain = this.genericTrainDao.getGenericTrainById(id);
        return convertGenTrainToGenTrainDto(genericTrain);
    }

    // this propagation 'cause not so frequently and important operations
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<GenericTrainDto> getAllGenericTrainDTOs() {

        List<GenericTrain> genericTrains = this.genericTrainDao.getAllGenericTrains();

        if(genericTrains == null || genericTrains.isEmpty()) {
            return null;  // TODO  ?????????????????????????????????
        }
        List<GenericTrainDto> dtoList = new ArrayList<>();
        for(GenericTrain gt : genericTrains) {
            GenericTrainDto dto = convertGenTrainToGenTrainDto(gt);
            dtoList.add(dto);
        }
        return dtoList;
    }

    private GenericTrainDto convertGenTrainToGenTrainDto(GenericTrain gt) {
        if (gt == null) return null;
        GenericTrainDto trainDto = new GenericTrainDto();
        trainDto.setNumber(gt.getNumber());
        trainDto.setRoute(gt.getRoute());
        trainDto.setNumOfPlazkartCars(gt.getNumOfPlazkartCars());
        trainDto.setNumOfSeatsInPlazkartCar(gt.getNumOfSeatsInPlazkartCar());
        trainDto.setNumOfCoopeCars(gt.getNumOfCoopeCars());
        trainDto.setNumOfSeatsInCoopeCar(gt.getNumOfSeatsInCoopeCar());
        trainDto.setNumOfSwCars(gt.getNumOfSwCars());
        trainDto.setNumOfSeatsInSwcar(gt.getNumOfSeatsInSwCar());

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

        List<RoutePointDto> rpDtoList = new ArrayList<>();
        for(RoutePoint rp : gt.getRoutePoints()) { // subselect optimization here (see "for(GenericTrain gt : genericTrains){" )
            RoutePointDto rpDto = new RoutePointDto();
            rpDto.setStationId(rp.getStation().getId());
            rpDto.setArrivalTime(Converter.convertLocalTimeToString(rp.getArrivalTime()));
            rpDto.setDepartTime(Converter.convertLocalTimeToString(rp.getDepartTime()));
            rpDto.setDaysFromTrainDepartToArrivalHere(rp.getDaysFromTrainDepartToArrivalHere());
            rpDto.setDaysFromTrainDepartToDepartFromHere(rp.getDaysFromTrainDepartToDepartFromHere());
            rpDtoList.add(rpDto);
        }
        trainDto.setRoutePointDtoList(rpDtoList);

        return trainDto;
    }

}
