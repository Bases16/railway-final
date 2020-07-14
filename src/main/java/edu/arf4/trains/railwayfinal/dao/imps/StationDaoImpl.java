package edu.arf4.trains.railwayfinal.dao.imps;

import edu.arf4.trains.railwayfinal.dao.StationDao;
import edu.arf4.trains.railwayfinal.model.Station;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.Transactional;

@Repository
@Transactional
public class StationDaoImpl implements StationDao {

    @Autowired
    private EntityManagerFactory emf;
    @Autowired
    private JtaTransactionManager transactionManager;


    @Override
    public Station getStationProxyById(Long id) {
        EntityManager em = emf.createEntityManager();
        Station station = em.getReference(Station.class, id);
        em.close();
        return station;
    }

    @Override
    public Station getStationById(Long id) {
        EntityManager em = emf.createEntityManager();
        Station station = em.find(Station.class, id);
        em.close();
        return station;
    }




}

