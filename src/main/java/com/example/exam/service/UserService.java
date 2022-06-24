package com.example.exam.service;

import com.example.exam.entity.UserEntity;
import com.example.exam.exception.NicknameException;
import com.example.exam.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void saveUser(String firstName, String secondName, String nickname, int age) {
        checkNickName(nickname);
        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName(firstName);
        userEntity.setSecondName(secondName);
        userEntity.setNickname(nickname);
        userEntity.setAge(age);
        userRepository.save(userEntity);
    }

    public void renameUser(String firstName,
                           String secondName,
                           String nickname,
                           String newNickname,
                           int age){
        Optional<UserEntity> userEntity = userRepository.findByNickname(nickname);
        if (userEntity.isPresent()) {
            checkNickName(newNickname);
            userEntity.get().setNickname(newNickname);
            userEntity.get().setFirstName(firstName);
            userEntity.get().setSecondName(secondName);
            userEntity.get().setAge(age);
            userRepository.save(userEntity.get());
        }
    }

    public void deleteUser(String nickname){
        Optional<UserEntity> userEntity = userRepository.findByNickname(nickname);
        if(userEntity.isPresent()){
            userRepository.delete(userEntity.get());
        }
    }

    public UserEntity userInfo(String nickname){
        Optional<UserEntity> userEntity = userRepository.findByNickname(nickname);
        return userEntity.orElse(null);
    }

    private void checkNickName(String nickname) {
        Optional<UserEntity> userEntity = userRepository.findByNickname(nickname);
        if (userEntity.isPresent()) {
            throw new NicknameException();
        }
    }
}
