package by.owm.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * Created by haria on 29.9.17.
 */
@Document(collection = "User")
public class UserEntity extends BaseEntity {

    @Id
    private String id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String token;
    private List<RoleEntity> roles;
    private LocalDateTime lastLogin;

    public UserEntity(@NotNull final String name, @NotNull final String surname, @NotNull final String password,
                      @NotNull final String email, @NotNull final List<RoleEntity> roles) {
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.email = email;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(@NotNull final String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(@NotNull final String token) {
        this.token = token;
    }

    public List<RoleEntity> getRoles(){
        return roles;
    }

    public void setRoles(List<RoleEntity> roles) {
        this.roles = roles;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }


    @Override
    public String toString() {
        return "UserEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", token='" + token + '\'' +
                ", roles=" + roles +
                '}';
    }
}
