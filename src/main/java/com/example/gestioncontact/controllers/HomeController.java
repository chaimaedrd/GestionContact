package com.example.gestioncontact.controllers;

import com.example.gestioncontact.entities.Contact;
import com.example.gestioncontact.entities.Groupe;
import com.example.gestioncontact.repositories.ContactRepo;
import com.example.gestioncontact.repositories.GroupeRepo;
import com.example.gestioncontact.services.ContactService;
import com.example.gestioncontact.services.GroupeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping({"", "/"})
public class HomeController {

    @Autowired
    private GroupeRepo groupeRepo;
    @Autowired
    private ContactRepo contactRepo;
    @Autowired
    private GroupeService groupeService;
    @Autowired
    ContactService contactService;

    @GetMapping({"", "/"})
    public String home(@RequestParam(value = "groupeOption",required = false) Long groupe ,Model model) {
        List<Groupe> groupeList = null;
        List<Contact> contactList = null;
        groupeList = groupeRepo.findAllByOrderByNom();
        contactList = contactRepo.findAllByOrderByNom();
        model.addAttribute("listGroupe", groupeList);
        model.addAttribute("listContact",contactList);
        if(groupe != null){
            model.addAttribute("groupeOption",groupe);
            model.addAttribute("groupeOptionContacts",groupeService.getContactsByGroupeId(groupe));
        }
        return "index";
    }
    @GetMapping({ "/error"})
    public String error() {
        return "error";
    }

    @GetMapping({ "/about"})
    public String about() {
        return "about";
    }

    @PostMapping("/assign-contacts")
    public String  assignContactsToGroup(@RequestParam("selectedGroup") Long groupId, @RequestParam("selectedContacts[]") Long[] contactIds){
        Groupe group = groupeService.getGroupById(groupId);
        List<Contact> contacts = contactService.getContactsByIds(contactIds);

        group.setContacts(contacts);
        groupeService.saveGroup(group);

        return "redirect:/";
    }
}
