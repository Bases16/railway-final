package edu.arf4.trains.railwayfinal.dao.implementations;

import edu.arf4.trains.railwayfinal.dao.interfaces.GenericDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManagerFactory;


@Repository
//@Transactional                   // T extends Serializable
public abstract class GenericDaoImpl <T> implements GenericDao<T> {

    @Autowired
    protected EntityManagerFactory emf;

    private Class<T> entityClass;

    public void setEntityClass(Class<T> entityClass) {
        this.entityClass = entityClass;
    }


}
