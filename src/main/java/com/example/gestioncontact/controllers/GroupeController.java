package com.example.gestioncontact.controllers;

import com.example.gestioncontact.entities.Contact;
import com.example.gestioncontact.entities.Groupe;
import com.example.gestioncontact.repositories.GroupeRepo;
import com.example.gestioncontact.services.GroupeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/groupes")
public class GroupeController {
    @Autowired
    private GroupeRepo groupeRepo;
    @Autowired
    private GroupeService groupeService;

    @ModelAttribute("groupe")
    public Groupe newGroupe(){
        return new Groupe();
    }

    @GetMapping("/create")
    public String showAddGroupeForm() {
        return "groupes/create";
    }

    @PostMapping("/create")
    public String addGroupe(@ModelAttribute("groupe") Groupe groupe, RedirectAttributes redirectAttributes) {
        if(groupeRepo.save(groupe) != null)
            redirectAttributes.addFlashAttribute("success","groupe ajouter avec success"); // message d une duree de vie egale a une seule requette http
        else
            redirectAttributes.addFlashAttribute("error","error");

        return "redirect:/groupes/create";
    }

    @GetMapping("/showAllGroupes")
    public String showAllGroupes(@RequestParam(value = "query",required = false) String query,Model model) {
        List<Groupe> groupeList = null;
        if(query == null){
            groupeList = groupeRepo.findAllByOrderByNom();

        }else{
                groupeList = groupeRepo.findByNom("%"+query+"%");
        }
        model.addAttribute("query", query);
        model.addAttribute("listGroupe", groupeList);
        return "groupes/showAllGroupes";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes){
        Groupe groupe = groupeService.delete(id);
        if(groupe != null)
            redirectAttributes.addFlashAttribute("success","groupe "+groupe.getNom()+" deleted successfuly");
        else
            redirectAttributes.addFlashAttribute("failed","Something went wrong ! please try again");
        return "redirect:/groupes/showAllGroupes";
    }

}
