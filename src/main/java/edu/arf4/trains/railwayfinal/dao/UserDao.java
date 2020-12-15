package edu.arf4.trains.railwayfinal.dao;

import edu.arf4.trains.railwayfinal.model.User;

public interface UserDao {


    User getUserById(String email);

    void addUser(User user);



}
