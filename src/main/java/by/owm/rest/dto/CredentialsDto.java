package by.owm.rest.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by haria on 11.10.17.
 */
public class CredentialsDto implements Serializable {

    private String email;
    private String password;

    public CredentialsDto() {
    }

    public CredentialsDto(@NotNull final String email, @NotNull final String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(@NotNull final String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(@NotNull final String password) {
        this.password = password;
    }

}
