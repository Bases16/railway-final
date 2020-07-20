package edu.arf4.trains.railwayfinal.dao.imps;

import edu.arf4.trains.railwayfinal.dao.StationDao;
import edu.arf4.trains.railwayfinal.model.SpecRoutePoint;
import edu.arf4.trains.railwayfinal.model.Station;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@Repository
public class StationDaoImpl implements StationDao {

    @Autowired
    private EntityManagerFactory emf;

    @Override
    public Long addStation(Station station) {
        EntityManager em = emf.createEntityManager();
        em.persist(station);
        em.close();
        return station.getId();
    }

    @Override
    public Station getStationById(Long id, boolean getProxy) {
        EntityManager em = emf.createEntityManager();
        Station station = null;
        if(getProxy) {
            station = em.getReference(Station.class, id);
        } else {
            station = em.find(Station.class, id);
        }
        em.close();
        return station;
    }


    @Override
    public List<SpecRoutePoint> getSrpListByStationId(Long id) {

        String query = "SELECT srp FROM SpecRoutePoint srp JOIN FETCH srp.routePoint WHERE srp.routePoint.station.id =:id" ;

        EntityManager em = emf.createEntityManager();
        List<SpecRoutePoint> srpList = null;

        srpList = em.createQuery(query, SpecRoutePoint.class).
                setParameter("id", id).
                getResultList();

        em.close();
        return srpList;
    }


}

