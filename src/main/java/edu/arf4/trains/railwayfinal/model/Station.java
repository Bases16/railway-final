package edu.arf4.trains.railwayfinal.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "station")
public class Station {

    @Id
    @GeneratedValue(generator = "MY_ID_GENERATOR")
    private Long id;
}
