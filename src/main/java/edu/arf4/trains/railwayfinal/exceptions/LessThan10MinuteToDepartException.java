package edu.arf4.trains.railwayfinal.exceptions;

public class LessThan10MinuteToDepartException extends Exception {

    public static final String ERROR_MSG = "Less than 10 min to train departure";

    public LessThan10MinuteToDepartException() {
        super(ERROR_MSG);
    }

}
