package com.realshovanshah.restroapi.auth.service;

import com.realshovanshah.restroapi.auth.model.VerificationToken;
import com.realshovanshah.restroapi.auth.repository.VerificationTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class VerificationTokenService {

    private final VerificationTokenRepository verificationTokenRepository;

    void saveVerificationToken(VerificationToken verificationToken){
        verificationTokenRepository.save(verificationToken);
    }
}
