package edu.arf4.trains.railwayfinal.dao;

import edu.arf4.trains.railwayfinal.model.GenericTrain;
import edu.arf4.trains.railwayfinal.model.Schedule;

import java.util.List;

public interface GenericTrainDao {


    /**
     * @return added train id
     */
    Long addGenericTrain(GenericTrain train);


    List<GenericTrain> getAllGenericTrains();

    GenericTrain getGenericTrainById(Long id);


//    Schedule getScheduleByGenTrainId(Long id);
//    GenericTrain getGenericTrainByNumber(String number);
}
