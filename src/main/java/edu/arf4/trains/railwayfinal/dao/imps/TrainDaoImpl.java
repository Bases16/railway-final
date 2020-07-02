package edu.arf4.trains.railwayfinal.dao.imps;

import edu.arf4.trains.railwayfinal.dao.TrainDao;
import edu.arf4.trains.railwayfinal.model.Train;
import edu.arf4.trains.railwayfinal.model.TrainCar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;

@Repository
//@Transactional
public class TrainDaoImpl implements TrainDao {

    @Autowired
    private EntityManagerFactory factory;

//    @PersistenceContext(unitName = "entityManagerFactory")
//    private EntityManager em;


//       // 1 - FOR READY ENTITY MANAGER   - good
//    @Override
//    public void persistTrain(Train train) {
//        em.persist(train);
//    }
//    @Override
//    public Train findTrainById(Long id) {
//        return em.find(Train.class, id);
//    }


//    // 2 - FOR READY ENTITY MANAGER trying start transaction    -  error
//    @Override
//    public void persistTrain(Train train) {
//        EntityTransaction transaction = em.getTransaction();
//        transaction.begin();
//
//
//        em.persist(train);
//
//        transaction.commit();
//    }
//    @Override
//    public Train findTrainById(Long id) {
//        EntityTransaction transaction = em.getTransaction();
//        transaction.begin();
//
//        Train train = em.find(Train.class, id);
//
//        transaction.commit();
//
//        return train;
//    }




//     // 3 - FOR FACTORY without starting Transaction   - error       ///////////////THE MOST INTERESTING CASE
//    @Override
//    public void persistTrain(Train train) {
//        EntityManager manager = factory.createEntityManager();
//        manager.persist(train);
//        manager.close();
//    }
//    @Override
//    public Train findTrainById(Long id) {
//        EntityManager manager = factory.createEntityManager();
//        Train train = manager.find(Train.class, id);
//        manager.close();
//        return train;
//    }



    // 4 - FOR FACTORY TRYING starting Transaction    -  good
    @Override
    public void persistTrain(Train train) {
        EntityManager manager = factory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();

        manager.persist(train);

        transaction.commit();

        manager.close();
    }
    @Override
    public Train findTrainById(Long id) {
        EntityManager manager = factory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();

        Train train = manager.find(Train.class, id);

        transaction.commit();
        manager.close();
        return train;
    }

}
