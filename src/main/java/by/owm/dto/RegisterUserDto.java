package by.owm.dto;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by haria on 13.10.17.
 */
public class RegisterUserDto extends UserDto {

    private String password;

    public RegisterUserDto(@NotNull final String name, final @NotNull String surname, final @NotNull String email,
                           final @NotNull String token, final @NotNull List<RoleDto> roles, final @NotNull String password) {
        super(name, surname, email, token, roles);
        this.password = password;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

}
