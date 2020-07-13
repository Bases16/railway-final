package edu.arf4.trains.railwayfinal.dao.imps;

import edu.arf4.trains.railwayfinal.dao.GenericTrainDao;
import edu.arf4.trains.railwayfinal.model.GenericTrain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@Repository
@Transactional
public class GenericTrainDaoImpl implements GenericTrainDao {

    @Autowired
    EntityManagerFactory factory;

    @Override
    public void addGenericTrain(GenericTrain genericTrain) {

        if(genericTrain.getRoutePoints().size() == 0) {
            throw new RuntimeException("attempting to persist genericTrain without routePoints");
        }
        EntityManager manager = factory.createEntityManager();
        manager.persist(genericTrain);
        manager.close();
    }
}
