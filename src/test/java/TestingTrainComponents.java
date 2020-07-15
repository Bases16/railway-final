import edu.arf4.trains.railwayfinal.config.DatabaseConfig;
import edu.arf4.trains.railwayfinal.dao.TrainDao;
import edu.arf4.trains.railwayfinal.model.Train;
import edu.arf4.trains.railwayfinal.model.TrainCar;
import edu.arf4.trains.railwayfinal.model.TrainCarType;
import edu.arf4.trains.railwayfinal.service.TrainService;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DatabaseConfig.class)
public class TestingTrainComponents {

    @Autowired
    TrainDao trainDao;
    @Autowired
    TrainService trainService;

    @Ignore
    @Test
    public void testingTrainDao() {
        Train train = new Train();
        train.setDepartDate(LocalDate.of(2021,1,10));

        TrainCar trainCar = new TrainCar();
        trainCar.setType(TrainCarType.COOPE);
        trainCar.setOrderOfCar(2);

        Map<Integer, Boolean> seats = new HashMap<>();
        seats.put(1, false);
        seats.put(2, true);
        seats.put(3, false);
        seats.put(4, false);
        seats.put(5, true);

        trainCar.setSeats(seats);

        train.getTrainCars().add(trainCar);
        trainCar.setTrain(train);

        trainDao.addTrain(train);

        Train trainFromDB = trainDao.findTrainById(train.getId());

        TrainCar trainCarFromDB = trainFromDB.getTrainCars().iterator().next();

        trainCarFromDB.getSeats().forEach( (k, v) -> System.out.println("key:" + k + " v:" + v) );
    }

    @Ignore
    @Test
    public void testingCalcDepartDatesForGenTrainByDates() {


    }

}