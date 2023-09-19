package fr.formation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import fr.formation.request.HelloFormRequest;

@Controller // Classe managée par SPRING comme Controller
public class HelloController {
    // @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @GetMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("user", "Jerem");

        return "index";
    }

    @GetMapping("/hello/{usr}")
    public String helloPv(@PathVariable(name = "usr") String username, Model model) {
        model.addAttribute("user", username);

        return "index";
    }

    // @PostMapping("/hello")
    // public String postHello(@RequestParam String username, @RequestParam String password, Model model) {
    //     model.addAttribute("user", username);

    //     return "index";
    // }

    @PostMapping("/hello")
    public String postHello(HelloFormRequest request, Model model) {
        model.addAttribute("user", request.getUsername());

        return "index";
    }
}
