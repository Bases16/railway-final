package edu.arf4.trains.railwayfinal.model;

import edu.arf4.trains.railwayfinal.util.Constants;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Station {

    @Id
    @GeneratedValue(generator = Constants.MY_ID_GENERATOR)
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true)
    private String name;

    public Station(String name) {
        this.name = name;
    }

    protected Station() {}


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
