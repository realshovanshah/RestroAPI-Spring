package com.realshovanshah.restroapi.auth.repository;

import com.realshovanshah.restroapi.auth.model.VerificationToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificationTokenRepository extends CrudRepository<VerificationToken, Long> {
}
