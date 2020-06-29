package edu.arf4.trains.railwayfinal.dao.implementations;

import edu.arf4.trains.railwayfinal.dao.interfaces.TrainDao;
import edu.arf4.trains.railwayfinal.model.Train;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Component
@Transactional
public class TrainDaoImpl implements TrainDao {

    @PersistenceContext(unitName = "entityManagerFactory")
    public EntityManager em;

    public TrainDaoImpl() {
        System.out.println("TrainDao was created");
    }

    @Override
    public void persist(Train train) {
        em.persist(train);
    }
}
