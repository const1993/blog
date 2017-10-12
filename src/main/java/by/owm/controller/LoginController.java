package by.owm.controller;

import by.owm.dto.CredentialsDto;
import by.owm.dto.RoleDto;
import by.owm.dto.UserDto;
import by.owm.entity.AccessTokenEntity;
import by.owm.entity.RoleEntity;
import by.owm.entity.UserEntity;
import by.owm.service.acessToken.AccessTokenService;
import by.owm.service.db.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping(value = "/api")
public class LoginController {

    private final UserService userServiceImpl;
    private final AccessTokenService accessTokenServiceImpl;

    @Autowired
    public LoginController(final UserService userServiceImpl, final AccessTokenService accessTokenServiceImpl) {
        this.userServiceImpl = userServiceImpl;
        this.accessTokenServiceImpl = accessTokenServiceImpl;
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> greet(@RequestBody final CredentialsDto credentials) {
        final UserEntity userEntity = userServiceImpl.findUserByEmailAndPassword(credentials.getEmail(), credentials.getPassword());
        if (userEntity == null) {
            return badRequest().build();
        }

        final AccessTokenEntity accessTokenEntity = accessTokenServiceImpl.createNewToken(userEntity);
        if (accessTokenEntity == null) {
            return badRequest().build();
        }

        final UserDto user = new UserDto(userEntity.getName(), userEntity.getSurname(), userEntity.getEmail(),
                accessTokenEntity.getToken(), new ArrayList<>());

        return ok().body(user);
    }

    @GetMapping("/create")
    public String greet() {

        List<RoleEntity> roles = new ArrayList<>();
        roles.add(new RoleEntity("USER"));
        boolean result = userServiceImpl.addNewUser("user", "user", "user", "user@user.ru", roles);

        return "login";
    }

    @GetMapping("/notallowed")
    public String no() {
        return "login";
    }
}
