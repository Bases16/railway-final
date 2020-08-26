package edu.arf4.trains.railwayfinal.model;

import edu.arf4.trains.railwayfinal.util.Constants;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Passenger {

    @Id
    @GeneratedValue(generator = Constants.MY_ID_GENERATOR)
    private Long id;

    @Column(nullable = false, updatable = false)
    private String firstName;

    @Column(nullable = false, updatable = false)
    private String lastName;

    @Column(nullable = false, updatable = false)
    private LocalDate dateOfBirth;


}
