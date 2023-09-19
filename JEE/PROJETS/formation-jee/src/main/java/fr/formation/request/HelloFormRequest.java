package fr.formation.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class HelloFormRequest {
    @NotBlank
    private String username;
    
    @NotBlank
    private String password;
}
