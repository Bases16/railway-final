package edu.arf4.trains.railwayfinal;

import edu.arf4.trains.railwayfinal.config.DatabaseConfig;
import edu.arf4.trains.railwayfinal.dao.implementations.StationDaoImpl;
import edu.arf4.trains.railwayfinal.dao.implementations.TrainDaoImpl;
import edu.arf4.trains.railwayfinal.dao.interfaces.StationDao;
import edu.arf4.trains.railwayfinal.dao.interfaces.TrainDao;
import edu.arf4.trains.railwayfinal.model.Train;
import edu.arf4.trains.railwayfinal.model.TrainCar;
import edu.arf4.trains.railwayfinal.model.TrainCarType;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class TrainyApp {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(DatabaseConfig.class);

        // BIG SHIT WITH INTERFACES AND IMPSS
        StationDao stationDao = context.getBean(StationDao.class);
        //EntityManagerFactory em = stationDao.em;
//        System.out.println(em);
        TrainDao trainDao = context.getBean(TrainDao.class);
//        em = trainDao.em;
//        System.out.println(em);



        Train train = new Train();
        train.setDepartDate(LocalDate.now());

        TrainCar car = new TrainCar();
        car.setType(TrainCarType.PLATZKART);

        Map<Integer, Boolean> seats = new HashMap<>();
        seats.put(1, true);
        seats.put(2, true);
        seats.put(3, false);
        seats.put(4, true);
        seats.put(5, true);
    //    car.setSeats(seats);

        //train.getTrainCars().add(car);

       // trainDao.persistCar(car);


        context.close();
    }
}
