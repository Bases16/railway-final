package edu.arf4.trains.railwayfinal.exceptions;

public class AlreadyRegisteredException extends Exception {

    public static final String ERROR_MSG = "The passenger has already registered on this train";

    public AlreadyRegisteredException() {
        super(ERROR_MSG);
    }
}
