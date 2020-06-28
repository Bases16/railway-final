package edu.arf4.trains.railwayfinal.dao.implementations;

import edu.arf4.trains.railwayfinal.dao.interfaces.StationDao;
import edu.arf4.trains.railwayfinal.model.Station;
import org.springframework.stereotype.Repository;

@Repository
public class StationDaoImpl extends GenericDaoImpl<Station> implements StationDao {


    private StationDaoImpl() {
        this.setEntityClass(Station.class);
        System.out.println("StationDaoImpl was created");
    }



}
