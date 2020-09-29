package edu.arf4.trains.railwayfinal.dao.imps;

import edu.arf4.trains.railwayfinal.dao.GenericTrainDao;
import edu.arf4.trains.railwayfinal.dto.GenericTrainDto;
import edu.arf4.trains.railwayfinal.model.GenericTrain;
import edu.arf4.trains.railwayfinal.model.Schedule;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import java.util.ArrayList;
import java.util.List;

//@Profile("main")
@Repository
public class GenericTrainDaoImpl implements GenericTrainDao {

    public static final Logger log = LoggerFactory.getLogger(GenericTrainDaoImpl.class);

//    @Autowired
//    EntityManagerFactory emf;

    @PersistenceContext(unitName = "entityManagerFactory", type = PersistenceContextType.TRANSACTION)
    private EntityManager em;



    //    @Autowired
//    JtaTransactionManager jtaTxManager; //почему не бесцветный как в trainDao??


    @Override
    public Long addGenericTrain(GenericTrain genericTrain)  {
//        EntityManager em = emf.createEntityManager();
        em.persist(genericTrain);


        // using flush() is okay 'cause this method is at the end of tx
        try {
            em.flush();
        } catch (RuntimeException e) {
            log.debug("CAUGHT THAT SHIT", e);
//            e.printStackTrace();
            throw e;
        }

//        em.flush();
        return genericTrain.getId();
    }


    @Override
    public GenericTrain getGenericTrainById(Long id) {
//        System.out.println(" getGenericTrainById aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
//        EntityManager em = emf.createEntityManager();
        GenericTrain genericTrain = em.find(GenericTrain.class, id);
//        Hibernate.initialize(genericTrain.getRoutePoints());
//        em.close();
        return genericTrain;
    }

    @Override
    public List<GenericTrain> getAllGenericTrains() {

        List<GenericTrain> genericTrains = new ArrayList<>();
//        EntityManager em = emf.createEntityManager();
        genericTrains = em.createQuery("SELECT gt FROM GenericTrain gt", GenericTrain.class).getResultList();

        // TODO getResultList throws exception if not found any. What to do???

        return genericTrains;
    }


    // WE COULD ADD EXTRA PARAMETER IN SUCH METHODS TO DEFINE THE FETCH TYPE
//    @Override
//    public GenericTrain getGenericTrainByNumber(String number) {
//        System.out.println(" getGenericTrainByNumber aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
//
//        String query = "SELECT gt FROM GenericTrain gt JOIN FETCH gt.routePoints WHERE gt.number =:number";
//
//        EntityManager em = emf.createEntityManager();
//        GenericTrain gt = em.createQuery(query, GenericTrain.class)
//                .setParameter("number", number)
//                .getSingleResult();
//        em.close();
//        return gt;
//    }

    // perhaps the method makes no sense if substitute it for one finding GenTrain without rouPoints initializing
//    @Override
//    public Schedule getScheduleByGenTrainId(Long id) {
//        System.out.println(" getScheduleByGenTrainId aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
//
//        String query = "SELECT gt.schedule FROM GenericTrain gt WHERE gt.id =:id";
//
//        EntityManager em = emf.createEntityManager();
//        Schedule schedule = em.createQuery(query, Schedule.class)
//                .setParameter("id", id)
//                .getSingleResult();
//        em.close();
//
//        return schedule;
//    }




}
