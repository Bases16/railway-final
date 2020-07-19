package edu.arf4.trains.railwayfinal.dao.imps;

import edu.arf4.trains.railwayfinal.dao.TrainDao;
import edu.arf4.trains.railwayfinal.model.Example;
import edu.arf4.trains.railwayfinal.model.Train;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.transaction.Status;

@Repository
//@Transactional
public class TrainDaoImpl implements TrainDao {

    @Autowired
    private EntityManagerFactory emf;

//    @PersistenceContext(unitName = "entityManagerFactory")
//    private EntityManager em;


    // // 1 - FOR READY ENTITY MANAGER   - good (with jta good too)   //but should we close EM explicitly???
    @Override
    public Long addTrain(Train train) {
        EntityManager em = emf.createEntityManager();
        em.persist(train);
        em.close();
        return train.getId();
    }
    @Override
    public Train findTrainById(Long id) {
        EntityManager em = emf.createEntityManager();
        Train train = em.find(Train.class, id);
        Hibernate.initialize(train.getTrainCars());
        em.close();
        return train;
    }

    @Override
    public void addExample(Example ex) {
        EntityManager em = emf.createEntityManager();
        em.persist(ex);
        em.close();
    }


//    // 4 - FOR FACTORY TRYING starting Transaction    -  good
//    @Override
//    public void persistTrain(Train train) {
    //
//
//        EntityManager em = null;
//        UserTransaction tx = transactionManager.getUserTransaction();
//        try {
//            tx.begin();
//            em = factory.createEntityManager();
//            em.persist(train);
//            tx.commit();
//        } catch (Exception ex) {
//            try {
//                if (tx.getStatus() == Status.STATUS_ACTIVE || tx.getStatus() == Status.STATUS_MARKED_ROLLBACK)
//                    tx.rollback();
//            } catch (Exception rbEx) {
//                System.err.println("Rollback of transaction failed, trace follows!");
//                rbEx.printStackTrace(System.err);
//            }
//            throw new RuntimeException(ex);
//        } finally {
//            if (em != null && em.isOpen()) {
//                em.close();
//            }
//        }
//    }
//    @Override
//    public Train findTrainById(Long id) {
//        EntityManager em = null;
//        UserTransaction tx = transactionManager.getUserTransaction();
//        Train train = null;
//        try {
//            tx.begin();
//            em = factory.createEntityManager();
//            train = em.find(Train.class, id);
//            tx.commit();
//        } catch (Exception ex) {
//            try {
//                if (tx.getStatus() == Status.STATUS_ACTIVE || tx.getStatus() == Status.STATUS_MARKED_ROLLBACK)
//                    tx.rollback();
//            } catch (Exception rbEx) {
//                System.err.println("Rollback of transaction failed, trace follows!");
//                rbEx.printStackTrace(System.err);
//            }
//            throw new RuntimeException(ex);
//        } finally {
//            if (em != null && em.isOpen()) {
//                em.close();
//            }
//        }
//        return train;
//    }

}
