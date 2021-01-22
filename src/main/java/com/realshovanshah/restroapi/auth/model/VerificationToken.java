package com.realshovanshah.restroapi.auth.model;

import com.realshovanshah.restroapi.auth.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;
import java.util.UUID;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VerificationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String token;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private User user;
    private Instant expiryDate;

    public VerificationToken(User user){
        this.user = user;
//        this.expiryDate = Instant.now();
        this.token = UUID.randomUUID().toString();
    }
}
