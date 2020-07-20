package edu.arf4.trains.railwayfinal.dao;

import edu.arf4.trains.railwayfinal.model.Example;
import edu.arf4.trains.railwayfinal.model.Train;

public interface TrainDao {

    Long addTrain(Train train);

    Train findTrainById(Long id);



    Long addExample(Example ex);
    Example findExample(Long id);

}
