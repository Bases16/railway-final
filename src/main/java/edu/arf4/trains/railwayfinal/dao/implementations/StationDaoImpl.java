package edu.arf4.trains.railwayfinal.dao.implementations;

import edu.arf4.trains.railwayfinal.dao.interfaces.StationDao;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class StationDaoImpl implements StationDao {

    @PersistenceContext(unitName = "entityManagerFactory")
    public EntityManager em;


    public StationDaoImpl() {
        System.out.println("Station dao was crated");
    }


}

