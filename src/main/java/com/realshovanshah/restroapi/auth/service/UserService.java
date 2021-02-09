package com.realshovanshah.restroapi.auth.service;

import com.realshovanshah.restroapi.auth.dto.AuthenticationResponse;
import com.realshovanshah.restroapi.auth.dto.LoginRequest;
import com.realshovanshah.restroapi.auth.model.User;
import com.realshovanshah.restroapi.auth.model.VerificationToken;
import com.realshovanshah.restroapi.auth.repository.UserRepository;
import com.realshovanshah.restroapi.auth.repository.VerificationTokenRepository;
import com.realshovanshah.restroapi.utils.ResturaApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.net.http.HttpClient;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;
    @Autowired
    private EmailService emailService;
    @Autowired
    private AuthenticationManager authenticationManager;


    @Autowired
    private VerificationTokenService verificationTokenService;

    public List<User> getAllUsers(){
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(user -> users.add(user));
        return users;
    }

    public void signUp(User user){
        final String encryptedPassword = passwordEncoder().encode(user.getPassword());
        user.setPassword(encryptedPassword);
        userRepository.save(user);
        final VerificationToken verificationToken = new VerificationToken(user);
        verificationTokenService.saveVerificationToken(verificationToken);
        System.out.println(user.getEmail());
        emailService.sendConfirmationMail(user.getEmail(), verificationToken.getToken(), "Thank you for registering. Please click on the below link to activate your account. "
                + "http://localhost:8080/api/auth/account-verification/");
    }

    public AuthenticationResponse login(LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
//        sessionRepository.findByIndexNameAndIndexValue(FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME, "admin@gmail.com").keySet().forEach(session -> System.out.println(((String) session)));
        return new AuthenticationResponse("test", loginRequest.getEmail());

    }


    public void updateUser(User user){
        userRepository.save(user);
    }

    public void deleteUser(long id) {
        List<VerificationToken> tokenList = new ArrayList<>();
        verificationTokenRepository.findAll().forEach(tokenList::add);

        int i = 0;
        while (i < tokenList.size()){
            if (!tokenList.get(i).getUser().getId().equals(id)){
                verificationTokenRepository.deleteById(tokenList.get(i).getId());
                userRepository.deleteById(id);
            }
            System.out.println("Can't Delete: The id " + id + " exists!");
            i++;
        }
        userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        userRepository.findAll().forEach(user -> System.out.println(user.getUsername()));
        final Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            return user.get();
        }
        else {
            throw new UsernameNotFoundException(String.format("User with email %s cannot be found.", email));
        }

//        user.orElseThrow(()-> new UsernameNotFoundException(String.format("User with email %s cannot be found.", email)));
//        return user.get();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public void verifyAccount(String token) {
        Optional<VerificationToken> verificationToken = verificationTokenRepository.findByToken(token);
        verificationToken.orElseThrow(() -> new ResturaApiException("Invalid Token"));
        getUserAndEnable(verificationToken.get());

    }

    @Transactional
    private void getUserAndEnable(VerificationToken verificationToken) {
        String username = verificationToken.getUser().getUsername();
        User user = userRepository.findByEmail(username).orElseThrow(() -> new ResturaApiException("User with email" + username + "not found"));
        user.setEnabled(true);
        userRepository.save(user);

    }

    public void resetPassword(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(()-> new ResturaApiException("User with email" + email + "not found"));
        Optional<VerificationToken> resetToken = verificationTokenRepository.findByUser(user);
        emailService.sendConfirmationMail(user.getEmail(), resetToken.get().getToken(), "Thank you for registering. Please click on the below link to reset your password. " +
                "http://localhost:8080/api/auth/reset-password/");
    }
}