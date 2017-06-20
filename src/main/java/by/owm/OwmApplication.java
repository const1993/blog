package by.owm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
public class OwmApplication {

    public static void main(String[] args) {
        SpringApplication.run(OwmApplication.class, args);
    }
}
