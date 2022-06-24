package com.example.exam.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class GetUserByNicknameRequest {

    @NotBlank
    private String nickname;

}
