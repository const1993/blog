package by.owm.dto;

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
    private String password;
    private List<RoleDto> roles;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<RoleDto> getRoles() {
        return roles;
    }

    public void setRoles(final List<RoleDto> roles) {
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
