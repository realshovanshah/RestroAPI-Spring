package com.realshovanshah.restroapi.auth.model;

import com.realshovanshah.restroapi.auth.model.User;

import javax.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
public class VerificationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String token;
    @OneToOne
    private User user;
    private Instant expiryDate;

    VerificationToken(User user){
        this.user = user;
//        this.expiryDate = Instant.now();
        this.token = UUID.randomUUID().toString();
    }
}
