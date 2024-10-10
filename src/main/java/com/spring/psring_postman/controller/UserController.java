package com.spring.psring_postman.controller;


import com.spring.psring_postman.dto.reponse.ApiResponse;
import com.spring.psring_postman.dto.reponse.UserReponse;
import com.spring.psring_postman.dto.request.UserCreationRequest;
import com.spring.psring_postman.dto.request.UserUpdateRequest;
import com.spring.psring_postman.entity.User;
import com.spring.psring_postman.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("users")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    ApiResponse<User> createUser(@RequestBody @Valid UserCreationRequest request) {
        ApiResponse<User> apiResponse = new ApiResponse<>();

        apiResponse.setResult(userService.createUser(request));
        return apiResponse;
    }

    @GetMapping
    List<User> getAllUsers() {
//        var authentication= SencurityContextHolder;
        SecurityContext context = SecurityContextHolder.getContext();

        log.info("UserName: {}", context.getAuthentication().getName());
//        context.getAuthentication().getPrincipal().;

        return userService.getAllUsers();
//        return  ApiResponse.<List<UserReponse>>builder()
//                .result(userService.getAllUsers())
//                .build();
    }

    @GetMapping("/{userId}")
    User getUser(@PathVariable("userId") String userId) {
        return userService.getUserById(userId);
    }

    @PutMapping("/{userId}")
    User updateUser(@PathVariable String userId, @RequestBody UserUpdateRequest request){
        return userService.updateUser(userId,request);
    }

    @DeleteMapping("/{userId}")
    String deleteUser(@PathVariable String userId){
        userService.deleteUserById(userId);
        return "User has been deleted!!";
    }
}
