package com.brotherhood.stocktaking.models.responses;

import com.brotherhood.stocktaking.models.entities.UserEntity;
import com.brotherhood.stocktaking.models.requests.LoginRequest;
import com.brotherhood.stocktaking.services.SecurityService;
import com.brotherhood.stocktaking.services.UserService;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import static com.brotherhood.stocktaking.repositories.UserSecurityRepositoryImpl.SWAGGER_TOKEN;

@Getter
@Setter
@Accessors(chain = true)
public class ResultResponse {
    private String token;
    private String message;
    private Object data;

    public ResultResponse(SecurityService securityService, String token, Object data) {
        int userId = securityService.isTokenValid(token);
        if (userId <= 0) {
            this.token = null;
            this.message = "Invalid token";
            this.data = null;
        } else {
            if (token.equals(SWAGGER_TOKEN)) {
                this.token = token;
            } else {
                this.token = securityService.generateToken(userId);
            }
            this.data = data;
            this.message = null;
        }
    }

    public ResultResponse(SecurityService securityService, int userId) {
        if (userId > 0) {
            this.token = securityService.generateToken(userId);
        } else {
            this.message = "Request error";
        }
    }

    public ResultResponse(SecurityService securityService, UserEntity userEntity) {
        if (userEntity != null && userEntity.getId() > 0) {
            this.token = securityService.generateToken(userEntity.getId());
        }
    }
}
