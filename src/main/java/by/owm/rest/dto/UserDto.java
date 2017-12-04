package by.owm.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
public class UserDto implements Serializable {

    private static final long serialVersionUID = 8267970814621983069L;

    private String name;
    private String surname;
    private String email;
    private String token;
    private List<RoleDto> roles;
}
