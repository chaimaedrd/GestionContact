package com.example.gestioncontact.repositories;

import com.example.gestioncontact.entities.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContactRepo extends JpaRepository<Contact,Long> {

    List<Contact> findAllByOrderByNom();
    @Query("SELECT c FROM #{#entityName} c WHERE c.nom LIKE ?1 OR c.prenom LIKE ?1 ORDER BY c.nom, c.prenom DESC" )
    List<Contact> findByNom(String nom);

    @Query("SELECT c FROM #{#entityName} c WHERE c.tel1 LIKE ?1 OR c.tel2 LIKE ?1 ORDER BY c.nom, c.prenom DESC" )
    List<Contact> findByTel1OrTel2(String tel);

    List<Contact> findByIdIn(Long[] contactIds);


}
