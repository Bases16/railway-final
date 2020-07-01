package edu.arf4.trains.railwayfinal;

import edu.arf4.trains.railwayfinal.config.DatabaseConfig;
import edu.arf4.trains.railwayfinal.dao.TrainDao;
import edu.arf4.trains.railwayfinal.model.Train;
import edu.arf4.trains.railwayfinal.model.TrainCar;
import edu.arf4.trains.railwayfinal.model.TrainCarType;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class TrainyApp {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(DatabaseConfig.class);


        TrainDao trainDao = context.getBean(TrainDao.class);

        Train train = new Train();
        train.setDepartDate(LocalDate.of(2021,1,10));

        TrainCar trainCar = new TrainCar();
        trainCar.setType(TrainCarType.PLATZKART);

        Map<Integer, Boolean> seats = new HashMap<>();
        seats.put(1, false);
        seats.put(2, true);
        seats.put(3, false);
        seats.put(4, true);
        seats.put(5, true);

        trainCar.setSeats(seats);

        train.getTrainCars().add(trainCar);
        trainCar.setTrain(train);

        trainDao.persistTrain(train);


        context.close();
    }
}
