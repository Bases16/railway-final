package edu.arf4.trains.railwayfinal.dao;

import edu.arf4.trains.railwayfinal.model.Passenger;

public interface PassengerDao {

    Passenger getPassengerById(Long id);

    Long addPassenger(Passenger passenger);

}
