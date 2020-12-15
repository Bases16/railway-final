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

    @Id
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


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }
}
