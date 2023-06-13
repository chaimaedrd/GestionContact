package com.example.gestioncontact.services;

import com.example.gestioncontact.entities.Contact;
import com.example.gestioncontact.entities.Groupe;
import com.example.gestioncontact.repositories.GroupeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupeService {
    @Autowired
    private GroupeRepo groupeRepo;
    public Groupe delete(Long id){
        Groupe groupe = groupeRepo.findById(id).get();
        groupeRepo.deleteById(groupe.getId());
        return groupe;
    }

    public Groupe getGroupById(Long groupId) {
        return groupeRepo.findById(groupId).orElseThrow(() -> new IllegalArgumentException("Invalid group ID: " + groupId));
    }

    public void saveGroup(Groupe group) {
        groupeRepo.save(group);
    }

    public List<Contact> getContactsByGroupeId(Long groupeId){
        Groupe groupe = groupeRepo.findById(groupeId).get();
        return groupe.getContacts().stream().toList();
    }
}
