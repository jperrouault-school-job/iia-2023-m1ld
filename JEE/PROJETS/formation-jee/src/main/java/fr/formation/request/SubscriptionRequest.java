package fr.formation.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SubscriptionRequest {
    private String username;
    private String password;
    private String email;
}
