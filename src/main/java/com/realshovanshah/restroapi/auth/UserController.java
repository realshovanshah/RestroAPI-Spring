package com.realshovanshah.restroapi.auth;

import com.realshovanshah.restroapi.auth.dto.AuthenticationResponse;
import com.realshovanshah.restroapi.auth.dto.LoginRequest;
import com.realshovanshah.restroapi.auth.model.User;
import com.realshovanshah.restroapi.auth.service.UserService;
import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.pojo.ApiStage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.SessionRepository;
import org.springframework.session.web.http.SessionRepositoryFilter;
import org.springframework.web.bind.annotation.*;
//import org.springframework.session.web.http.HttpSessionStrategy;

import javax.annotation.PostConstruct;
import java.security.Principal;
import java.util.List;

@Api(name = "Users Api", description = "Provides methods that interacts with the user database.", stage = ApiStage.RC)
@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private UserService userService;


    @PostConstruct
    void test() {
        System.out.println("debug postconstruct");
    }

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

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody LoginRequest loginRequest){
        return userService.login(loginRequest);
    }

    @DeleteMapping("/user/{id}")
    public void deleteCategory(@PathVariable Long id){
        userService.deleteUser(id);
    }


}
