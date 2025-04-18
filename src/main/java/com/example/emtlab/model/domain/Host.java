package com.example.emtlab.model.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Host {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String surname;
    @ManyToOne
    private Country country;

    @ManyToMany
    @JoinTable(
            name = "hosts_guests",
            joinColumns = @JoinColumn(name = "host"),
            inverseJoinColumns = @JoinColumn(name = "guest"))
    List<Guest> historyOfGuests;

    public Host() {
        this.historyOfGuests = new ArrayList<>();
    }

    public Host(String name, String surname, Country country) {
        this.name = name;
        this.surname = surname;
        this.country = country;
        this.historyOfGuests = new ArrayList<>();
    }
}
