package edu.arf4.trains.railwayfinal.exceptions;

public class NoTicketsLeftException extends Exception {

    public static final String ERROR_MSG = "There are no tickets left for this car type";

    public NoTicketsLeftException() {
        super(ERROR_MSG);
    }

}
