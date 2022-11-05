package com.graduation.project.controller;


import com.graduation.project.argumentResolver.Login;
import com.graduation.project.dto.*;
import com.graduation.project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    @PostMapping("/user/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return userService.login(request);
    }

    @PostMapping("/user/refresh")
    public LoginResponse refresh(@Valid @RequestBody TokenRefreshRequest request) {
        return userService.refresh(request);
    }

    @GetMapping("/user/info")
    public UserInfoResponse getUserInfo(@Login Long id) {
        return userService.getUserInfo(id);
    }

    @GetMapping("/user/hint")
    public String getHint(String loginId) {
        return userService.getHint(loginId);
    }

    @PostMapping("/user/answer-to-hint")
    public String answerToHint(@RequestBody AnswerToHintRequest request) {
        return userService.answerToHint(request);
    }

    @PutMapping("/user/find-password")
    public void findPassword(@Login Long userId, @RequestBody FindPasswordRequest request) {
        userService.findPassword(userId, request);
    }
}
