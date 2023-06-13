package com.example.gestioncontact.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Groupe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    //auto-incrementing
    private Long id;

    //@Column(nullable = false)   // not null
    private String nom;

    @ManyToMany(cascade = CascadeType.ALL) //PERSIST : ensures that they are all "groupe"&"contact" saved in the database together.
    @JoinTable(
            joinColumns = @JoinColumn(name = "groupe_id"),
            inverseJoinColumns = @JoinColumn(name = "contact_id")
    )
    private Collection<Contact> contacts;
}
