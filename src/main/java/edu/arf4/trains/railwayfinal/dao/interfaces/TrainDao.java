package edu.arf4.trains.railwayfinal.dao.interfaces;

import edu.arf4.trains.railwayfinal.model.Train;
import edu.arf4.trains.railwayfinal.model.TrainCar;

public interface TrainDao {

    void persistTrain(Train train);

    void persistCar(TrainCar car);

}
