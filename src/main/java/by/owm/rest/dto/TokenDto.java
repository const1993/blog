package by.owm.rest.dto;

import java.io.Serializable;

/**
 * Created by haria on 30.11.17.
 */
public class TokenDto implements Serializable {

    private String token;

    public TokenDto() {

    }

    public TokenDto(final String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
