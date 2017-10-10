package by.owm.controller;

import by.owm.dto.UserDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/api")
public class LoginController {

    @PostMapping("/login")
    public String greet(@RequestBody final UserDto user) {


        return "login";
    }

    @GetMapping("/notallowed")
    public String no() {
        return "login";
    }
}
