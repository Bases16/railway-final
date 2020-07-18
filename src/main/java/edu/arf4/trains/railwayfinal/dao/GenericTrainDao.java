package edu.arf4.trains.railwayfinal.dao;

import edu.arf4.trains.railwayfinal.model.GenericTrain;
import edu.arf4.trains.railwayfinal.model.Schedule;

public interface GenericTrainDao {


    /**
     * @return added train id
     */
    Long addGenericTrain(GenericTrain train);

    GenericTrain getGenericTrainByNumber(String number);

    GenericTrain getGenericTrainById(Long id);

    Schedule getScheduleByGenTrainId(Long id);
}
