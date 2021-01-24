package com.realshovanshah.restroapi.auth;

import com.realshovanshah.restroapi.auth.dto.AuthenticationResponse;
import com.realshovanshah.restroapi.auth.dto.LoginRequest;
import com.realshovanshah.restroapi.auth.model.User;
import com.realshovanshah.restroapi.auth.service.UserService;
import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.pojo.ApiStage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//import org.springframework.session.web.http.HttpSessionStrategy;

import java.security.Principal;
import java.util.List;

@Api(name = "Users Api", description = "Provides methods that interacts with the user database.", stage = ApiStage.RC)
@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private UserService userService;



    @ApiMethod
    @GetMapping("/users")
    public List<User> getAllUsers(Principal principal){
//        System.out.println(principal.getName());
        return userService.getAllUsers();
//        System.out.println(getAllUsers().get(0).get);
    }

    @ApiMethod
    @PostMapping("/signup")
    public User signup(@RequestBody User user){
        userService.signUp(user);
        return (User) userService.loadUserByUsername(user.getUsername());
    }

    @GetMapping("account-verification/{token}")
    public ResponseEntity<String> verifyAccount(@PathVariable String token){
        userService.verifyAccount(token);
        return new ResponseEntity<>("Account Activated Sucessfully", HttpStatus.OK);
    }

    @PostMapping("/login")
    public void login(@RequestBody LoginRequest loginRequest){
        char c = 0;
        AuthenticationResponse response = userService.login(loginRequest);
//        response.setAuthToken((String) sessionRepository.findByIndexNameAndIndexValue(FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME, "admin@gmail.com").keySet().stream().findFirst().get());
//        return response;

    }

    @DeleteMapping("/user/{id}")
    public void deleteCategory(@PathVariable Long id){
        userService.deleteUser(id);
    }


}
