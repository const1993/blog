package by.owm.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
//TODO figure out: how this annotation influence on dispatcher. Maybe need additional configuration
//@EnableWebMvc
@EnableSpringDataWebSupport
public class AppConfig {
}
