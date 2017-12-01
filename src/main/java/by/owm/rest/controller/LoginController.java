package by.owm.rest.controller;

import by.owm.domain.acessToken.AccessTokenService;
import by.owm.domain.db.UserService;
import by.owm.domain.entity.AccessTokenEntity;
import by.owm.domain.entity.RoleEntity;
import by.owm.domain.entity.UserEntity;
import by.owm.rest.dto.CredentialsDto;
import by.owm.rest.dto.RegisterUserDto;
import by.owm.rest.dto.TokenDto;
import by.owm.rest.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping(value = "/api")
public class LoginController {

    private static final String USER = "USER";

    private final UserService userService;
    private final AccessTokenService tokenService;

    @Autowired
    public LoginController(final UserService userService, final AccessTokenService tokenService) {
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody final CredentialsDto credentials) {
        final UserEntity userEntity = userService.findUserByEmailAndPassword(credentials.getEmail(), credentials.getPassword());
        if (userEntity == null) {
            return notFound().build();
        }

        final AccessTokenEntity accessTokenEntity = tokenService.createNewToken(userEntity);
        if (accessTokenEntity == null) {
            return notFound().build();
        }

        userEntity.setToken(accessTokenEntity.getToken());
        userEntity.setLastLogin(LocalDateTime.now());
        boolean result = userService.updateUser(userEntity);
        if (!result) {
            return badRequest().build();
        }

        final UserDto user = new UserDto(userEntity.getName(), userEntity.getSurname(), userEntity.getEmail(),
                accessTokenEntity.getToken(), new ArrayList<>());

        return ok().body(user);
    }

    @PostMapping("/checkToken")
    public ResponseEntity<UserDto> checkToken(@RequestBody final TokenDto tokenDto) {
        final UserEntity userEntity = userService.findUserByToken(tokenDto.getToken());
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
        final UserEntity userEntity = userService.findUserByToken(tokenDto.getToken());
        if (userEntity == null) {
            return ok().body(new UserEntity());
        }

        userEntity.setToken("");
        boolean result = userService.updateUser(userEntity);
        if (!result) {
            return badRequest().build();
        }

        return ok().body(new UserEntity());
    }

    @PostMapping("/create")
    public ResponseEntity<UserDto> create(@RequestBody final RegisterUserDto user) {

        final List<RoleEntity> roles = new ArrayList<>();
        roles.add(new RoleEntity(USER));
        boolean result = userService.addNewUser(user.getName(), user.getSurname(), user.getPassword(), user.getEmail(), roles);

        if (!result) {
            return badRequest().build();
        }

        return ok().body(user);
    }

    @GetMapping("/notallowed")
    public String no() {
        return "login";
    }
}
