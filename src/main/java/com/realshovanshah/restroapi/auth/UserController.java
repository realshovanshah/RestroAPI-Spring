package com.realshovanshah.restroapi.auth;

import com.realshovanshah.restroapi.auth.model.User;
import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.pojo.ApiStage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(name = "Users Api", description = "Provides methods that interacts with the user database.", stage = ApiStage.RC)
@RestController("/api/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiMethod
    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
//        System.out.println(getAllUsers().get(0).get);
    }

    @ApiMethod
    @PostMapping("/user")
    public User createUser(@RequestBody User user){
        userService.createUser(user);
        return (User) userService.loadUserByUsername(user.getUsername());
    }

    @DeleteMapping("/user/{id}")
    public void deleteCategory(@PathVariable Long id){
        userService.deleteUser(id);
    }

}
