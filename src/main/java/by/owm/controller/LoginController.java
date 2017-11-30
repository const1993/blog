package by.owm.controller;

import by.owm.dto.CredentialsDto;
import by.owm.dto.RegisterUserDto;
import by.owm.dto.TokenDto;
import by.owm.dto.UserDto;
import by.owm.entity.AccessTokenEntity;
import by.owm.entity.RoleEntity;
import by.owm.entity.UserEntity;
import by.owm.service.acessToken.AccessTokenService;
import by.owm.service.db.UserService;
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
        userServiceImpl.updateUser(userEntity);

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

        LocalDateTime lastLogin = userEntity.getLastLogin();
        LocalDateTime now = LocalDateTime.now();
        if (now.minusHours(2).isAfter(lastLogin)) {
            return ResponseEntity.badRequest().build();
        }

        final UserDto user = new UserDto(userEntity.getName(), userEntity.getSurname(), userEntity.getEmail(),
                userEntity.getToken(), new ArrayList<>());

        return ok().body(user);
    }

    @PostMapping("/create")
    public ResponseEntity<UserDto> create(@RequestBody final RegisterUserDto user) {

        List<RoleEntity> roles = new ArrayList<>();
        roles.add(new RoleEntity("USER"));
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
