package fr.formation.demoscreenshare.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hello")
public class HelloApiController {
    @GetMapping
    public String hello() {
        return "hello";
    }
}
