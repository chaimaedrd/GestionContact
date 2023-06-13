package com.example.gestioncontact.services;

import com.example.gestioncontact.entities.Contact;
import com.example.gestioncontact.repositories.ContactRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ContactService {
    @Autowired
    private ContactRepo contactRepo;

    public Contact delete(Long id){
        Contact contact = contactRepo.findById(id).get();
        contactRepo.deleteById(contact.getId());
        return contact;
    }

    public List<Contact> getContactsByIds(Long[] contactIds) {
        // Implement the logic to fetch contacts from the database based on the provided IDs
        return contactRepo.findByIdIn(contactIds);
    }

}
