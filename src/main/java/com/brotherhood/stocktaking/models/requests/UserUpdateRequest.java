package com.brotherhood.stocktaking.models.requests;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Getter
@Accessors(chain = true)
public class UserUpdateRequest {
    private String email;
    private String groupName;
}
