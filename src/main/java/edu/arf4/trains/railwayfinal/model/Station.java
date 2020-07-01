package edu.arf4.trains.railwayfinal.model;


import edu.arf4.trains.railwayfinal.util.Constants;

import javax.persistence.*;

@Entity
public class Station {

    @Id
    @GeneratedValue(generator = Constants.MY_ID_GENERATOR)
    private Long id;

    private String name;
}
