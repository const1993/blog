package by.owm.rest.controller;

import by.owm.rest.dto.CredentialsDto;
import by.owm.rest.dto.RegisterUserDto;
import by.owm.rest.dto.TokenDto;
import by.owm.rest.dto.UserDto;
import by.owm.domain.entity.AccessTokenEntity;
import by.owm.domain.entity.RoleEntity;
import by.owm.domain.entity.UserEntity;
import by.owm.domain.acessToken.AccessTokenService;
import by.owm.domain.db.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping(value = "/api")
public class LoginController {

    public static final String USER = "USER";
    private final UserService userServiceImpl;
    private final AccessTokenService accessTokenServiceImpl;

    @Autowired
    public LoginController(final UserService userServiceImpl, final AccessTokenService accessTokenServiceImpl) {
        this.userServiceImpl = userServiceImpl;
        this.accessTokenServiceImpl = accessTokenServiceImpl;
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody final CredentialsDto credentials) {
        final UserEntity userEntity = userServiceImpl.findUserByEmailAndPassword(credentials.getEmail(), credentials.getPassword());
        if (userEntity == null) {
            return notFound().build();
        }

        final AccessTokenEntity accessTokenEntity = accessTokenServiceImpl.createNewToken(userEntity);
        if (accessTokenEntity == null) {
            return notFound().build();
        }

        userEntity.setToken(accessTokenEntity.getToken());
        userEntity.setLastLogin(LocalDateTime.now());
        boolean result = userServiceImpl.updateUser(userEntity);
        if(!result) {
            return badRequest().build();
        }

        final UserDto user = new UserDto(userEntity.getName(), userEntity.getSurname(), userEntity.getEmail(),
                accessTokenEntity.getToken(), new ArrayList<>());

        return ok().body(user);
    }

    @PostMapping("/checkToken")
    public ResponseEntity<UserDto> checkToken(@RequestBody final TokenDto tokenDto) {
        final UserEntity userEntity = userServiceImpl.findUserByToken(tokenDto.getToken());
        if (userEntity == null) {
            return notFound().build();
        }

        final LocalDateTime lastLogin = userEntity.getLastLogin();
        final LocalDateTime now = LocalDateTime.now();
        if (now.minusHours(2).isAfter(lastLogin)) {
            return ResponseEntity.badRequest().build();
        }

        final UserDto user = new UserDto(userEntity.getName(), userEntity.getSurname(), userEntity.getEmail(),
                userEntity.getToken(), new ArrayList<>());

        return ok().body(user);
    }

    @PostMapping("/logout")
    public ResponseEntity logout(@RequestBody final TokenDto tokenDto) {
        final UserEntity userEntity = userServiceImpl.findUserByToken(tokenDto.getToken());
        if (userEntity == null) {
            return ok().body(new UserEntity());
        }

        userEntity.setToken("");
        boolean result = userServiceImpl.updateUser(userEntity);
        if(!result) {
            return badRequest().build();
        }

        return ok().body(new UserEntity());
    }

    @PostMapping("/create")
    public ResponseEntity<UserDto> create(@RequestBody final RegisterUserDto user) {

        final List<RoleEntity> roles = new ArrayList<>();
        roles.add(new RoleEntity(USER));
        boolean result = userServiceImpl.addNewUser(user.getName(), user.getSurname(), user.getPassword(), user.getEmail(), roles);

        if(!result) {
            return badRequest().build();
        }

        return ok().body(user);
    }

    @GetMapping("/notallowed")
    public String no() {
        return "login";
    }
}
