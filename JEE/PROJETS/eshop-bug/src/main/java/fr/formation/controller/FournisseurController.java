package fr.formation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.formation.request.FournisseurRequest;
import fr.formation.service.FournisseurService;

@Controller
@RequestMapping("/fournisseur")
public class FournisseurController {
    private static final String REDIRECT_URL = "redirect:/fournisseur";
    private static final String ATTR_FOURNISSEUR = "fournisseur";

    private final FournisseurService srvFournisseur = new FournisseurService();

    @GetMapping
    public String findAll() {
        return "not implemented";
    }

    @GetMapping("/details")
    public String findById(@RequestParam int id, Model model) {
        model.addAttribute(ATTR_FOURNISSEUR, this.srvFournisseur.findDetailedById(id));
        
        return "fournisseur/details";
    }

    @GetMapping("/ajouter")
    public String add() {
        return "fournisseur/edit";
    }

    @PostMapping("/ajouter")
    public String add(FournisseurRequest fournisseurRequest) {
        this.srvFournisseur.save(null, fournisseurRequest);

        return REDIRECT_URL;
    }

    @GetMapping("/modifier")
    public String edit(@RequestParam int id, Model model) {
        model.addAttribute(ATTR_FOURNISSEUR, this.srvFournisseur.findById(id));

        return "fournisseur/edit";
    }

    @PostMapping("/modifier")
    public String edit(@RequestParam int id, FournisseurRequest fournisseurRequest) {
        this.srvFournisseur.save(id, fournisseurRequest);

        return REDIRECT_URL;
    }

    @GetMapping("/supprimer")
    public String deleteById(@RequestParam int id) {
        this.srvFournisseur.deleteById(id);

        return REDIRECT_URL;
    }

    @ModelAttribute("pageActive")
    public String getPageActive() {
        return ATTR_FOURNISSEUR;
    }
}
