package edu.arf4.trains.railwayfinal.dao.imps;

import edu.arf4.trains.railwayfinal.dao.GenericTrainDao;
import edu.arf4.trains.railwayfinal.model.GenericTrain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@Repository
@Transactional
public class GenericTrainDaoImpl implements GenericTrainDao {

    @Autowired
    EntityManagerFactory emf;
    @Autowired
    JtaTransactionManager transactionManager;

    @Override
    public void addGenericTrain(GenericTrain genericTrain) {
        EntityManager manager = emf.createEntityManager();
        manager.persist(genericTrain);
        manager.close();
    }

    @Override
    public GenericTrain getGenericTrainByNumber(String number) {

        String query = "SELECT gt FROM GenericTrain gt JOIN FETCH gt.routePoints WHERE gt.number =:number";

        EntityManager em = emf.createEntityManager();
        GenericTrain gt = em.createQuery(query, GenericTrain.class)
                .setParameter("number", number)
                .getSingleResult();
        em.close();
        return gt;

    }



}
