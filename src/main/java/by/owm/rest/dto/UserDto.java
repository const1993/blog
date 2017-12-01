package by.owm.rest.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * Created by haria on 2.10.17.
 */
public class UserDto implements Serializable {

    private String name;
    private String surname;
    private String email;
    private String token;
    private List<RoleDto> roles;

    public UserDto(@NotNull final String name, @NotNull final String surname, @NotNull final String email,
                   @NotNull final  String token,
                   @NotNull final List<RoleDto> roles) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.token = token;
        this.roles = roles;
    }

    public String getName() {
        return name;
    }

    public void setName(@NotNull final String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(@NotNull final String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(@NotNull final String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(@NotNull final String token) {
        this.token = token;
    }

    public List<RoleDto> getRoles() {
        return roles;
    }

    public void setRoles(@NotNull final List<RoleDto> roles) {
        this.roles = roles;
    }
}
