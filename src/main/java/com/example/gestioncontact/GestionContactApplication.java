package com.example.gestioncontact;

import com.example.gestioncontact.entities.Contact;
import com.example.gestioncontact.entities.Groupe;
import com.example.gestioncontact.repositories.ContactRepo;
import com.example.gestioncontact.repositories.GroupeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class GestionContactApplication {

    @Autowired
    private ContactRepo contactRepo;
    @Autowired
    private GroupeRepo groupeRepo;
    public static void main(String[] args) {
        SpringApplication.run(GestionContactApplication.class, args);
    }
/*
    @Override
    public void run(String... args) throws Exception {

        Contact c1 = Contact.builder()
                .nom("chaimae")
                .prenom("dardouri")
                .adresse("Al Hoceima")
                .tel1("0666666")
                .tel2("0777777")
                .emailPerso("chaimae@gmail.com")
                .emailPro("chaimaedrdr@gmail.com")
                .genre("Female")
                .groupes(null)
                .build();
        Contact c2 = Contact.builder()
                .nom("romayssae")
                .prenom("dardouri")
                .adresse("Al Hoceima")
                .tel1("00000000")
                .tel2("6666666")
                .emailPerso("romayssae@gmail.com")
                .emailPro("romayssae@gmail.com")
                .genre("Female")
                .groupes(null)
                .build();

        Groupe g1 = Groupe.builder()
                .nom("famille")
                .contacts(null)
                .build();

        Groupe g2 = Groupe.builder()
                .nom("friends")
                .contacts(null)
                .build();

        contactRepo.save(c1);
        contactRepo.save(c2);

        groupeRepo.save(g1);
        groupeRepo.save(g2);


    }*/
}
