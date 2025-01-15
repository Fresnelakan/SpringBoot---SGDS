package com.sgds.optimisation_trajet_dechets.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sgds.optimisation_trajet_dechets.Model.Utilisateur;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(Model model) {
        Utilisateur utilisateur = new Utilisateur();
        model.addAttribute("user", utilisateur);
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        Utilisateur utilisateur = new Utilisateur();
        model.addAttribute("user", utilisateur);
        return "register";
    }

    @GetMapping("/")
    public String index() {
        return "redirect:/login";
    }

    @GetMapping("/souscripteur")
    public String souscripteur() {
        return "souscripteur";
    }

    @GetMapping("/agent")
    public String agent() {
        return "agent";
    }
}