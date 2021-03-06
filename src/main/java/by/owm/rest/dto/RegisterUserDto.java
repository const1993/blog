package by.owm.rest.dto;

import lombok.Data;

import java.util.List;

@Data
public class RegisterUserDto extends UserDto {

    private static final long serialVersionUID = 4404320447359426231L;

    private String password;

    public RegisterUserDto(final String name,
                           final String surname,
                           final String email,
                           final String token,
                           final List<RoleDto> roles,
                           final String password) {
        super(name, surname, email, token, roles);
        this.password = password;
    }
}
