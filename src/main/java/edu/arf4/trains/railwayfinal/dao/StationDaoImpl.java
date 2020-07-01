package edu.arf4.trains.railwayfinal.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManagerFactory;
import javax.transaction.Transactional;

@Repository
@Transactional
public class StationDaoImpl implements StationDao {

    @Autowired
    public EntityManagerFactory em;




}

