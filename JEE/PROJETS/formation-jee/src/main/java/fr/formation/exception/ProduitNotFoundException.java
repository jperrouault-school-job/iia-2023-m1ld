package fr.formation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Le produit n'existe pas.")
public class ProduitNotFoundException extends RuntimeException {
    
}
