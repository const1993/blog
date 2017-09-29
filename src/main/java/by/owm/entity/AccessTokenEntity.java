package by.owm.entity;

import javax.validation.constraints.NotNull;

/**
 * Created by haria on 29.9.17.
 */
public class AccessTokenEntity {

    private String token;

    @NotNull
    public String setToken(final String token) {
        return this.token = token;
    }

    @NotNull
    public String getToken() {
        return token;
    }
}
