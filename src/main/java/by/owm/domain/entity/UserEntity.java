package by.owm.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "User")
public class UserEntity implements Serializable {

    private static final long serialVersionUID = -6871891971516377552L;

    @Id
    private String id;
    private String name;
    private String surname;
    private String password;
    private String email;
    private String token;
    private List<RoleEntity> roles;
    private LocalDateTime lastLogin;

    public UserEntity(final String name,
                      final String surname,
                      final String password,
                      final String email,
                      final List<RoleEntity> roles) {
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.email = email;
        this.roles = roles;
    }
}
