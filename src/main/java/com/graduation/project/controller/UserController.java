package com.graduation.project.controller;


import com.graduation.project.dto.SignupRequest;
import com.graduation.project.dto.UserGetResponse;
import com.graduation.project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users/{userId}")
    public ResponseEntity<UserGetResponse> getUser(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getUser(userId));
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<Void> removeUsers(@PathVariable Long userId) {
        userService.removeUser(userId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/user/signup")
    public void signup(@RequestBody SignupRequest request) {
        userService.signup(request);
    }
}
