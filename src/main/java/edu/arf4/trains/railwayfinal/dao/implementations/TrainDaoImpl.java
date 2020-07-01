package edu.arf4.trains.railwayfinal.dao.implementations;

import edu.arf4.trains.railwayfinal.dao.interfaces.TrainDao;
import edu.arf4.trains.railwayfinal.model.Train;
import edu.arf4.trains.railwayfinal.model.TrainCar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Component
@Transactional
public class TrainDaoImpl implements TrainDao {

    @Autowired
    public EntityManagerFactory em;

    public TrainDaoImpl() {
        System.out.println("TrainDao was created");
    }

    @Override
    public void persistTrain(Train train) {
        EntityManager man = em.createEntityManager();

        man.getTransaction().begin();

        man.persist(train);
        man.flush();

        man.getTransaction().commit();
    }

    @Override
    public void persistCar(TrainCar car) {
        EntityManager man = em.createEntityManager();

        man.getTransaction().begin();

        man.persist(car);
//        man.flush();

        man.getTransaction().commit();


    }
}
