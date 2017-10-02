package by.owm.controller;

import by.owm.dto.UserDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/api")
public class LoginController {

    @PostMapping("/login")
    public String greet(@RequestBody final UserDto user) {


        return "login";
    }

    @PostMapping("/notallowed")
    public String no() {
        return "login";
    }
}
