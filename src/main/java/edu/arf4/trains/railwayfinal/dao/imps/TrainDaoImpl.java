package edu.arf4.trains.railwayfinal.dao.imps;

import edu.arf4.trains.railwayfinal.dao.TrainDao;
import edu.arf4.trains.railwayfinal.model.SpecRoutePoint;
import edu.arf4.trains.railwayfinal.model.Train;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

//@Profile("main")
@Repository
//@Transactional
public class TrainDaoImpl implements TrainDao {

//    @Autowired
//    private EntityManagerFactory emf;

    @PersistenceContext(unitName = "entityManagerFactory")
    private EntityManager em;


    // // 1 - FOR READY ENTITY MANAGER   - good (with jta good too)   //but should we close EM explicitly???
    @Override
    public Long addTrain(Train train) {
//        EntityManager em = emf.createEntityManager();
        em.persist(train);
//        em.close();
        return train.getId();
    }
    @Override
    public Train getTrainById(Long id) {
        System.out.println("findTrainById aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
//        EntityManager em = emf.createEntityManager();
        Train train = em.find(Train.class, id);
//        Hibernate.initialize(train.getTrainCars());
//        em.close();
        return train;
    }

    @Override
    public List<Train> getTrainsByGenTrainIdAndDates(Long genTrainId, LocalDate start, LocalDate end) {

        String query = "SELECT tr FROM Train tr WHERE tr.genericTrain.id =:id " +
                       "AND (tr.departDate >= :start AND tr.departDate < :end) order by tr.departDate ASC";

        List<Train> trains = null;
        trains = em.createQuery(query, Train.class)
                .setParameter("id", genTrainId)
                .setParameter("start", start)
                .setParameter("end", end)
                .getResultList();

        return trains;
    }

    // COUNT ALL TRAINS DEPARTING FROM OR ARRIVING AT THE STATION IN DATE RANGE
    @Override
    public List<SpecRoutePoint> getSrpListByStationId(Long id, boolean isFor2Stations, LocalDateTime start, LocalDateTime end) {

        String query = "SELECT srp FROM SpecRoutePoint srp JOIN FETCH srp.routePoint " +
               "WHERE srp.routePoint.station.id = :id AND ( (srp.departDatetime >= :start AND srp.departDatetime < :end  )"  +
                                                                                         " OR "                              +
                                                           "(srp.arrivalDatetime >= :start AND srp.arrivalDatetime < :end) )";

        String query2 = "SELECT srp FROM SpecRoutePoint srp JOIN FETCH srp.routePoint " +
               "WHERE srp.routePoint.station.id = :id AND (srp.departDatetime >= :start AND srp.departDatetime < :end)";

        String finalQuery = isFor2Stations ? query2 : query;

//        EntityManager em = emf.createEntityManager();
        List<SpecRoutePoint> srpList = null;

        srpList = em.createQuery(finalQuery, SpecRoutePoint.class)
                .setParameter("id", id)
                .setParameter("start", start)
                .setParameter("end", end)
                .getResultList();

//        em.close();
        return srpList;
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
