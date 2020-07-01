package edu.arf4.trains.railwayfinal.dao;

import edu.arf4.trains.railwayfinal.model.Train;
import edu.arf4.trains.railwayfinal.model.TrainCar;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
public class TrainDaoImpl implements TrainDao {

    @PersistenceContext(unitName = "entityManagerFactory")
    public EntityManager em;


    @Override
    public void persistTrain(Train train) {
//        em.getTransaction().begin();
        em.persist(train);
//        em.getTransaction().commit();
    }

    @Override
    public void persistCar(TrainCar car) {

        em.getTransaction().begin();

        em.persist(car);

        em.getTransaction().commit();


    }
}
