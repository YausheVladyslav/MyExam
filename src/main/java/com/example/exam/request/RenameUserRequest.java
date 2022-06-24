package com.example.exam.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RenameUserRequest {

    private String firstName;
    private String secondName;
    private String nickname;
    private String newNickname;
    private int age;

}
