package edu.arf4.trains.railwayfinal.service;

import edu.arf4.trains.railwayfinal.dao.StationDao;
import edu.arf4.trains.railwayfinal.dao.TrainDao;
import edu.arf4.trains.railwayfinal.dto.TrainDto;
import edu.arf4.trains.railwayfinal.model.SpecRoutePoint;
import edu.arf4.trains.railwayfinal.model.Station;
import edu.arf4.trains.railwayfinal.model.Train;
import edu.arf4.trains.railwayfinal.util.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Set;

@Service
public class TicketService {

    private final TrainDao trainDao;
    private final StationDao stationDao;

    @Autowired
    public TicketService(TrainDao trainDao, StationDao stationDao) {
        this.trainDao = trainDao;
        this.stationDao = stationDao;
    }

    @Transactional
    public void buyTicket(TrainDto trainDto, Long passengerId) {

        {
            LocalDateTime departTime = Converter.convertStringToLocalDateTime(trainDto.getLocalSrcDepartDateTime());
            if (LocalDateTime.now().isAfter(departTime.minusMinutes(10))) {
                throw new RuntimeException("LESS THAN 10 MIN TO TRAIN DEPARTURE"); //todo  CREATE CUSTOM EXCEPTION ??
            }
        }

        String[] ar = trainDto.getLocalRoute().split(" ");
        String fromName = ar[0];
        String toName = ar[2];

        Station from = this.stationDao.getStationByName(fromName);
        Station to = this.stationDao.getStationByName(toName);

        Train train = this.trainDao.getTrainById(trainDto.getId());
        Set<SpecRoutePoint> srPoints = train.getSpecRoutePoints();

        for (SpecRoutePoint point : srPoints) {
            if (point.getRoutePoint().getStation() == from) {
                if (point.getTicketsLeft() == 0) {
                    throw new RuntimeException("THERE'S NO TICKETS LEFT"); //todo  CREATE CUSTOM EXCEPTION ??
                }
            }
        }










    }


}
