package fr.formation.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@Builder @AllArgsConstructor @NoArgsConstructor
public class AuthRequest {
    @NotBlank
    private String username;
    
    @NotBlank
    private String password;
}
