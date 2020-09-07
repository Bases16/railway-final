package edu.arf4.trains.railwayfinal.dao.imps;

import edu.arf4.trains.railwayfinal.dao.PassengerDao;
import edu.arf4.trains.railwayfinal.model.Passenger;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

@Repository
public class PassengerDaoImpl implements PassengerDao {

    @PersistenceContext(unitName = "entityManagerFactory", type = PersistenceContextType.TRANSACTION)
    private EntityManager em;


    @Override
    public Passenger getPassengerById(Long id) {

        Passenger passenger = em.find(Passenger.class, id);

        return passenger;
    }
}
