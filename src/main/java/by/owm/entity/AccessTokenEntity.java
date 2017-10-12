package by.owm.entity;

import javax.validation.constraints.NotNull;

/**
 * Created by haria on 29.9.17.
 */
public class AccessTokenEntity {

    private String token;
    private String userId;

    public AccessTokenEntity(final @NotNull String token, final @NotNull String userId) {
        this.token = token;
        this.userId = userId;
    }

    @NotNull
    public String getToken() {
        return token;
    }

    @NotNull
    public String getUserId() {
        return userId;
    }
}
