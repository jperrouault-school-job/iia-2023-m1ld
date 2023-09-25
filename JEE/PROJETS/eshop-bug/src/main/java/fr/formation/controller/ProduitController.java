package fr.formation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.formation.request.ProduitRequest;
import fr.formation.service.FournisseurService;
import fr.formation.service.ProduitService;

@Controller
@RequestMapping("/produit")
public class ProduitController {
    private static final String REDIRECT_URL = "redirect:/produit";

    @Autowired
    private ProduitService srvProduit;

    @Autowired
    private FournisseurService srvFournisseur;

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("produits", this.srvProduit.findAll());

        return "produit/list";
    }

    @GetMapping("/ajouter")
    public String add(Model model) {
        model.addAttribute("fournisseurs", this.srvFournisseur.findAll());
        
        return "produit/edit";
    }

    @PostMapping("/ajouter")
    public String add(ProduitRequest produitRequest) {
        this.srvProduit.save(null, produitRequest);

        return REDIRECT_URL;
    }

    @GetMapping("/modifier")
    public String edit(@RequestParam int id, Model model) {
        model.addAttribute("produit", this.srvProduit.findById(id));
        model.addAttribute("fournisseurs", this.srvFournisseur.findAll());

        return "produit/edit";
    }

    @PostMapping("/modifier")
    public String edit(@RequestParam int id, ProduitRequest produitRequest) {
        this.srvProduit.save(id, produitRequest);

        return REDIRECT_URL;
    }

    @GetMapping("/supprimer")
    public String deleteById(@RequestParam int id) {
        this.srvProduit.deleteById(id);

        return REDIRECT_URL;
    }

    @ModelAttribute("pageActive")
    public String getPageActive() {
        return "produit";
    }
}
