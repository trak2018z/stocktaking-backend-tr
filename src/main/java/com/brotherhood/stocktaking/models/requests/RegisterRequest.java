package com.brotherhood.stocktaking.models.requests;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class RegisterRequest {
    private String login;
    private String name;
    private String surname;
    private String email;
    private String groupName;
    private String password;
}
