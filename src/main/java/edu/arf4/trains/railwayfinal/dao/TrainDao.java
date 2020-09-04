package edu.arf4.trains.railwayfinal.dao;

import edu.arf4.trains.railwayfinal.model.SpecRoutePoint;
import edu.arf4.trains.railwayfinal.model.Train;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface TrainDao {

    Long addTrain(Train train);

    Train getTrainById(Long id);

    List<Train> getTrainsByGenTrainIdAndDates(Long genTrainId, LocalDate start, LocalDate end);

    List<SpecRoutePoint> getSrpListByStationId(Long id, boolean isFor2Stations, LocalDateTime start, LocalDateTime end);


}
