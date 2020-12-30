package edu.arf4.trains.railwayfinal.dao.imps;

import edu.arf4.trains.railwayfinal.dao.StationDao;
import edu.arf4.trains.railwayfinal.model.SpecRoutePoint;
import edu.arf4.trains.railwayfinal.model.Station;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import java.util.List;

//@Profile("main")
@Repository
public class StationDaoImpl implements StationDao {

    public static final Logger log = LoggerFactory.getLogger(StationDaoImpl.class);

//    @Autowired
//    private EntityManagerFactory emf;

    @PersistenceContext(unitName = "entityManagerFactory", type = PersistenceContextType.TRANSACTION)
    private EntityManager em;

    @Override
    public Long addStation(Station station) {
//        EntityManager em = emf.createEntityManager();
        em.persist(station);
        log.debug("AFTER PERSIST");
//        em.close();
        return station.getId();
    }

    @Override
    public void deleteStation(Long id) {
        Station station = getStationById(id, true);
        em.remove(station);
    }

    @Override
    public Station getStationById(Long id, boolean getProxy) {
//        EntityManager em = emf.createEntityManager();
        Station station = null;
        if (getProxy) {
            station = em.getReference(Station.class, id);
        } else {
            station = em.find(Station.class, id);
        }
//        em.close();
        return station;
    }

    @Override
    public Station getStationByName(String name) {
//        EntityManager em = emf.createEntityManager();

        String query = "SELECT st FROM Station st WHERE st.name = :name";

        Station station = em.createQuery(query, Station.class).setParameter("name", name).getSingleResult();

//        em.close();
        return station;
    }


}

