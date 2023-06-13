package com.example.gestioncontact.repositories;

import com.example.gestioncontact.entities.Contact;
import com.example.gestioncontact.entities.Groupe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupeRepo extends JpaRepository<Groupe,Long> {
    List<Groupe> findAllByOrderByNom();
    @Query("SELECT g FROM #{#entityName} g WHERE g.nom LIKE ?1 ORDER BY g.nom DESC" )
    List<Groupe> findByNom(String nom);
}
