package edu.arf4.trains.railwayfinal.dao.imps;

import edu.arf4.trains.railwayfinal.dao.GenericTrainDao;
import edu.arf4.trains.railwayfinal.model.GenericTrain;
import edu.arf4.trains.railwayfinal.model.Schedule;
import org.hibernate.Hibernate;
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
    public Long addGenericTrain(GenericTrain genericTrain) {
        EntityManager manager = emf.createEntityManager();
        manager.persist(genericTrain);
        manager.close();
        return genericTrain.getId();
    }

    // WE COULD ADD EXTRA PARAMETER IN SUCH METHODS TO DEFINE THE FETCH TYPE
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

    @Override
    public GenericTrain getGenericTrainById(Long id) {
        EntityManager em = emf.createEntityManager();
        GenericTrain genericTrain = em.find(GenericTrain.class, id);
        Hibernate.initialize(genericTrain.getRoutePoints());
        em.close();
        return genericTrain;
    }

    // perhaps the method makes no sense if substitute it for one finding GenTrain without rouPoints initializing
    @Override
    public Schedule getScheduleByGenTrainId(Long id) {

        String query = "SELECT gt.schedule FROM GenericTrain gt WHERE gt.id =:id";

        EntityManager em = emf.createEntityManager();
        Schedule schedule = em.createQuery(query, Schedule.class)
                .setParameter("id", id)
                .getSingleResult();
        em.close();

        return schedule;
    }

}
