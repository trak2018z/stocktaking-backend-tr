package com.brotherhood.stocktaking.models.requests;

import lombok.Getter;

@Getter
public class LoginRequest {
    private String login;
    private String password;
}
