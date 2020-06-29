package edu.arf4.trains.railwayfinal.model;


import util.Constants;

import javax.persistence.*;

@Entity
//@Table(name = "station")
public class Station {

    @Id
    @GeneratedValue(generator = Constants.MY_ID_GENERATOR)
    private Long id;

    private String name;
}
