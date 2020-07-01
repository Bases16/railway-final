package edu.arf4.trains.railwayfinal.util;

public interface Constants {

    //modifiers  {public, static, final} is redundant for interface fields
    String MY_ID_GENERATOR = "MY_ID_GENERATOR";

    Integer NUM_OF_PLAZKART_CARS = 2;
    Integer NUM_OF_COOPE_CARS = 2;
    Integer NUM_OF_SV_CARS = 2;

    Integer NUM_OF_SEATS_IN_PLAZKART_CAR = 4;
    Integer NUM_OF_SEATS_IN_COOPE_CAR = 2;
    Integer NUM_OF_SEATS_IN_SV_CAR = 1;

    Integer NUM_OF_TRAIN_CARS_IN_TRAIN = NUM_OF_PLAZKART_CARS + NUM_OF_COOPE_CARS + NUM_OF_SV_CARS;

}
