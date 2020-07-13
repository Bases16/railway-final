package edu.arf4.trains.railwayfinal.service;

import edu.arf4.trains.railwayfinal.dao.GenericTrainDao;
import edu.arf4.trains.railwayfinal.dto.GenericTrainDto;
import edu.arf4.trains.railwayfinal.model.GenericTrain;
import edu.arf4.trains.railwayfinal.model.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenericTrainService {

    @Autowired
    GenericTrainDao genericTrainDao;

    public void createGenericTrain(GenericTrainDto genericTrainDto) {


        Schedule schedule = new Schedule();
        GenericTrain genericTrain = new GenericTrain(schedule);

        genericTrainDao.addGenericTrain(genericTrain);





    }









}
