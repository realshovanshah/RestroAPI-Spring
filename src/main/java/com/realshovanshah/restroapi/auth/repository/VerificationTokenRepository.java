package com.realshovanshah.restroapi.auth.repository;

import com.realshovanshah.restroapi.auth.model.User;
import com.realshovanshah.restroapi.auth.model.VerificationToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VerificationTokenRepository extends CrudRepository<VerificationToken, Long> {
    Optional<VerificationToken> findByToken(String token);
    Optional<VerificationToken> findByUser(User user);
}
