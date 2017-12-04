package by.owm.rest.controller;

import by.owm.domain.acessToken.AccessTokenService;
import by.owm.domain.user.UserService;
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

import static java.time.LocalDateTime.now;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.apache.commons.lang.StringUtils.EMPTY;
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
    public LoginController(final UserService userService, final AccessTokenService accessTokenService) {
        this.userService = userService;
        this.tokenService = accessTokenService;
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
        userEntity.setLastLogin(now());
        boolean result = userService.updateUser(userEntity);
        if (!result) {
            return badRequest().build();
        }

        final UserDto user = new UserDto(
                userEntity.getName(),
                userEntity.getSurname(),
                userEntity.getEmail(),
                accessTokenEntity.getToken(),
                emptyList());

        return ok().body(user);
    }

    @PostMapping("/checkToken")
    public ResponseEntity<UserDto> checkToken(@RequestBody final TokenDto tokenDto) {
        final UserEntity userEntity = userService.findUserByToken(tokenDto.getToken());
        if (userEntity == null) {
            return notFound().build();
        }

        final LocalDateTime lastLogin = userEntity.getLastLogin();
        if (now().minusHours(2).isAfter(lastLogin)) {
            return badRequest().build();
        }

        final UserDto user = new UserDto(
                userEntity.getName(),
                userEntity.getSurname(),
                userEntity.getEmail(),
                userEntity.getToken(),
                emptyList());

        return ok().body(user);
    }

    @PostMapping("/logout")
    public ResponseEntity logout(@RequestBody final TokenDto tokenDto) {
        return userService.logOut(tokenDto.getToken())
                ? ok().body(EMPTY)
                : badRequest().build();
    }

    @PostMapping("/create")
    public ResponseEntity<UserDto> create(@RequestBody final RegisterUserDto user) {
        return userService.addNewUser(
                user.getName(),
                user.getSurname(),
                user.getPassword(),
                user.getEmail(),
                singletonList(new RoleEntity(USER))
        )
                ? ok().body(user)
                : badRequest().build();
    }

    @GetMapping("/notallowed")
    public String no() {
        return "login";
    }
}
