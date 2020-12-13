package edu.arf4.trains.railwayfinal.model;


import edu.arf4.trains.railwayfinal.util.Constants;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "users")
public class User {

//    @Id
//    @GeneratedValue(generator = Constants.MY_ID_GENERATOR)
//    private Long id;

    @Id
    @NotNull
    private String email;
    @NotNull
    private String password;
    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status;
    @NotNull
    @Enumerated(EnumType.STRING)
    private Role role;
    @OneToOne(fetch = FetchType.LAZY)
    private Passenger passenger;


//    @Enumerated(EnumType.STRING)
//    @ElementCollection
//    @CollectionTable(name = "roles")
//    @Column(name = "role", nullable = false)
//    private Set<Role> roles = new HashSet<>();









}
