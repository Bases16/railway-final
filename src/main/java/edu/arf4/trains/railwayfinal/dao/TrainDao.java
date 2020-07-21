package edu.arf4.trains.railwayfinal.dao;

import edu.arf4.trains.railwayfinal.model.Example;
import edu.arf4.trains.railwayfinal.model.SpecRoutePoint;
import edu.arf4.trains.railwayfinal.model.Train;

import java.time.LocalDateTime;
import java.util.List;

public interface TrainDao {

    Long addTrain(Train train);

    Train findTrainById(Long id);

    List<SpecRoutePoint> getSrpListByStationId(Long id, LocalDateTime start, LocalDateTime end);




    Long addExample(Example ex);
    Example findExample(Long id);

}
