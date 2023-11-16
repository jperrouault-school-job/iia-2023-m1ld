package fr.formation.demokafka;

import java.util.function.Consumer;
import java.util.function.Function;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import lombok.extern.log4j.Log4j2;

@SpringBootApplication
@Log4j2
public class DemoKafkaApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoKafkaApplication.class, args);
    }

    @Bean
    Consumer<String> onHelloConsumer() {
        return (evt) -> {
            log.debug("Message reçu : {}", evt);
        };
    }

    @Bean
    Function<String, String> onUppercaseFunction() {
        return (evt) -> {
            log.debug("Message reçu à transformer : {}", evt);
            return evt.toUpperCase();
        };
    }
}
