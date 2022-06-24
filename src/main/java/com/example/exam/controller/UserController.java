package com.example.exam.controller;

import com.example.exam.entity.UserEntity;
import com.example.exam.request.GetUserByNicknameRequest;
import com.example.exam.request.RenameUserRequest;
import com.example.exam.request.UserRequest;
import com.example.exam.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/save-user")
    public ResponseEntity<Void> saveUser(@Valid @RequestBody UserRequest userRequest) {
        userService.saveUser(userRequest.getFirstName(),
                userRequest.getSecondName(),
                userRequest.getNickname(),
                userRequest.getAge());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get-user")
    public ResponseEntity<UserEntity> userInfo(@Valid @RequestBody GetUserByNicknameRequest getUserByNicknameRequest) {
        return ResponseEntity.ok(userService.userInfo(getUserByNicknameRequest.getNickname()));
    }

    @DeleteMapping("/delete-user")
    public ResponseEntity<Void> deleteUser(@Valid @RequestBody GetUserByNicknameRequest getUserByNicknameRequest) {
        userService.deleteUser(getUserByNicknameRequest.getNickname());
        return ResponseEntity.ok().build();
    }

    @PutMapping("/rename-user")
    public ResponseEntity<Void> renameUser(@RequestBody RenameUserRequest renameUserRequest) {
        userService.renameUser(
                renameUserRequest.getFirstName(),
                renameUserRequest.getSecondName(),
                renameUserRequest.getNickname(),
                renameUserRequest.getNewNickname(),
                renameUserRequest.getAge());
        return ResponseEntity.ok().build();
    }
}
