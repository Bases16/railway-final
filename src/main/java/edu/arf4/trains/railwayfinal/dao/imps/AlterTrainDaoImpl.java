package edu.arf4.trains.railwayfinal.dao.imps;

import edu.arf4.trains.railwayfinal.dao.TrainDao;
import edu.arf4.trains.railwayfinal.model.Example;
import edu.arf4.trains.railwayfinal.model.SpecRoutePoint;
import edu.arf4.trains.railwayfinal.model.Train;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.time.LocalDateTime;
import java.util.List;

@Profile("alter")
@Repository
public class AlterTrainDaoImpl implements TrainDao {

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
        System.out.println("findTrainById aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        EntityManager em = emf.createEntityManager();
        Train train = em.find(Train.class, id);
        Hibernate.initialize(train.getTrainCars());
        em.close();
        return train;
    }


    @Override
    public List<SpecRoutePoint> getSrpListByStationId(Long id, LocalDateTime start, LocalDateTime end) {

        String query = "SELECT srp FROM SpecRoutePoint srp JOIN FETCH srp.routePoint " +
                "WHERE srp.routePoint.station.id =:id AND ( (srp.departDatetime >= :start AND srp.departDatetime < :end  )" +
                " OR   (srp.arrivalDatetime >= :start AND srp.arrivalDatetime < :end) )";

        EntityManager em = emf.createEntityManager();
        List<SpecRoutePoint> srpList = null;

        srpList = em.createQuery(query, SpecRoutePoint.class)
                .setParameter("id", id)
                .setParameter("start", start)
                .setParameter("end", end)
                .getResultList();

        em.close();
        return srpList;
    }

    @Transactional
    @Override
    public Long addExample(Example ex) {
        EntityManager em = emf.createEntityManager();
        em.persist(ex);
        em.close();
        return ex.getId();
    }
    @Override
    public Example findExample(Long id) {
        EntityManager em = emf.createEntityManager();
        Example ex = em.find(Example.class, id);
        em.close();
        return ex;
    }



}
