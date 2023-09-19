package fr.formation;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DemoContainer implements CommandLineRunner {
    // @Autowired
    private final Guitariste guitariste;

    public DemoContainer(Guitariste guitariste) {
        this.guitariste = guitariste;
    }
    
    @Override
    public void run(String... args) throws Exception {
        guitariste.jouer();
    }
}
