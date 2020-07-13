package edu.arf4.trains.railwayfinal.dao;

import edu.arf4.trains.railwayfinal.model.Train;

public interface TrainDao {

    void addTrain(Train train);

    Train findTrainById(Long id);



}
