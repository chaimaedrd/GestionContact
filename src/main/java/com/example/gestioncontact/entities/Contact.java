package com.example.gestioncontact.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)     //auto-incrementing
    private Long id;
    private String nom;
    private String prenom;
    private String tel1;
    private String tel2;
    private String adresse;
    private String emailPerso;
    private String emailPro;
    private String genre;

    @ManyToMany(mappedBy = "contacts", fetch = FetchType.LAZY,cascade = CascadeType.ALL)    // it is loaded on-demand and not immediately fetched from the database
    private Collection<Groupe> groupes;
}
