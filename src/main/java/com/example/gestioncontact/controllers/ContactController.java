package com.example.gestioncontact.controllers;

import com.example.gestioncontact.entities.Contact;
import com.example.gestioncontact.entities.Groupe;
import com.example.gestioncontact.repositories.ContactRepo;
import com.example.gestioncontact.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/contacts")
public class ContactController {
    @Autowired
    private ContactRepo contactRepo;
    @Autowired
    private ContactService contactService;

    @ModelAttribute("contact")
    public Contact newContact(){
        return new Contact();
    }

    //afficher le formulaire d'ajout
    @GetMapping("/create")
    public String showAddContactForm() {
        return "contacts/create";
    }

    @PostMapping("/create")
    public String addContact(@ModelAttribute("contact") Contact contact, RedirectAttributes redirectAttributes) {
        if(contactRepo.save(contact) != null)
            redirectAttributes.addFlashAttribute("success","contact ajouter avec success"); // message d une duree de vie egale a une seule requette http
        else
            redirectAttributes.addFlashAttribute("error","error");

        return "redirect:/contacts/create";
    }

    @GetMapping("/showAllContacts")
    public String showAllContacts(@RequestParam(value = "query",required = false) String query,Model model) {
        List<Contact> contactList = null;
        if(query == null){
            contactList = contactRepo.findAllByOrderByNom();

        }else{
            if(query.matches("\\d+")){
                System.out.println(query);
                contactList = contactRepo.findByTel1OrTel2("%"+query+"%");
            }
            else{
                contactList = contactRepo.findByNom("%"+query+"%");
            }
        }
        model.addAttribute("query", query);
        model.addAttribute("listContact", contactList);
        return "contacts/showAllContacts";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes){
        Contact contact = contactService.delete(id);
        if(contact != null)
            redirectAttributes.addFlashAttribute("success","contact "+contact.getPrenom()+" "+contact.getNom()+" deleted successfuly");
        else
            redirectAttributes.addFlashAttribute("failed","Something went wrong ! please try again");
        return "redirect:/contacts/showAllContacts";
    }

    @PostMapping("/update/{id}")
    public String updateContact(@ModelAttribute("contact") Contact contact, RedirectAttributes redirectAttributes){
        if(contactRepo.saveAndFlush(contact) != null)
            redirectAttributes.addFlashAttribute("success","contact modifier avec success"); // message d une duree de vie egale a une seule requette http
        else
            redirectAttributes.addFlashAttribute("error","erreur lors de la modification");

        return "redirect:/contacts/showAllContacts";
    }
    @GetMapping("/update/{id}")
    public String update(@PathVariable Long id,Model model) {
        model.addAttribute("contact",contactRepo.findById(id).get());
        return "contacts/update";
    }

}



