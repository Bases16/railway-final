package edu.arf4.trains.railwayfinal.dao.imps;

import edu.arf4.trains.railwayfinal.dao.UserDao;
import edu.arf4.trains.railwayfinal.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext(unitName = "entityManagerFactory", type = PersistenceContextType.TRANSACTION)
    private EntityManager em;

    @Override
    public User getUserById(String email) {
        return em.find(User.class, email);
    }

    @Override
    public void addUser(User user) {
        em.persist(user);
    }
}
