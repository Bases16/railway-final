package edu.arf4.trains.railwayfinal.dao.imps;

import edu.arf4.trains.railwayfinal.dao.GenericTrainDao;
import edu.arf4.trains.railwayfinal.model.GenericTrain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;

@Profile("alter")
@Repository
public class AlterGenericTrainDaoImpl implements GenericTrainDao {

    @Autowired
    EntityManagerFactory emf;

    @Override
    public Long addGenericTrain(GenericTrain genericTrain) {
        EntityManager em = emf.createEntityManager();
        em.persist(genericTrain);
        em.close();
        return genericTrain.getId();
    }


    @Override
    public GenericTrain getGenericTrainById(Long id) {
        System.out.println(" getGenericTrainById aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        EntityManager em = emf.createEntityManager();
        GenericTrain genericTrain = em.find(GenericTrain.class, id);
//        Hibernate.initialize(genericTrain.getRoutePoints());
        em.close();
        return genericTrain;
    }

    @Override
    public List<GenericTrain> getAllGenericTrains() {

        List<GenericTrain> genericTrains = new ArrayList<>();
        EntityManager em = emf.createEntityManager();
        genericTrains = em.createQuery("SELECT gt FROM GenericTrain gt", GenericTrain.class).getResultList();

        return genericTrains;
    }
}
