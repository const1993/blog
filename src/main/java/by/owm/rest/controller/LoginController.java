package by.owm.rest.controller;

import by.owm.domain.mapper.Mapper;
import by.owm.domain.model.Role;
import by.owm.domain.model.User;
import by.owm.domain.user.UserService;
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

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.apache.commons.lang.StringUtils.EMPTY;
import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping(value = "/api")
public class LoginController {

    private static final String USER = "USER";

    private final UserService userService;
    private final Mapper mapper;

    @Autowired
    public LoginController(final UserService userService,
                           final Mapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody final CredentialsDto credentials) {
        final User user = userService.logIn(credentials.getEmail(), credentials.getPassword());
        return ok().body(mapper.map(user, UserDto.class));
    }

    @PostMapping("/checkToken")
    public ResponseEntity<UserDto> checkToken(@RequestBody final TokenDto tokenDto) {
        final User user = userService.checkToken(tokenDto.getToken());
        return user == null ? badRequest().build() : ok().body(mapper.map(user, UserDto.class));
    }

    @PostMapping("/logout")
    public ResponseEntity logout(@RequestBody final TokenDto tokenDto) {
        return userService.logOut(tokenDto.getToken())
                ? ok().body("{}")
                : badRequest().build();
    }

    @PostMapping("/create")
    public ResponseEntity<UserDto> create(@RequestBody final RegisterUserDto user) {
        return userService.addNewUser(
                user.getName(),
                user.getSurname(),
                user.getPassword(),
                user.getEmail(),
                singletonList(new Role(USER))
        )
                ? ok().body(user)
                : badRequest().build();
    }

    //TODO: remove this in future
    @GetMapping("/create-stub-user")
    public ResponseEntity<UserDto> createStubUser() {
        return create(new RegisterUserDto(
                "user",
                "user",
                "user@gmail.ru",
                EMPTY,
                emptyList(),
                "pass"
        ));
    }

    @GetMapping("/notallowed")
    public String no() {
        return "login";
    }
}
