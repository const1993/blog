package by.owm.config;

import by.owm.rest.filter.AuthenticationTokenProcessingFilter;
import by.owm.rest.filter.CorsFilter;
import by.owm.domain.acessToken.AccessTokenService;
import by.owm.domain.jpa.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;

@Configuration
@EnableWebSecurity
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthenticationProvider mongoAuthenticationProvider;
    private final AccessTokenService accessTokenService;
    private final RolesRepository rolesRepository;

    @Bean
    public CorsFilter corsFilter() {
        CorsFilter filter = new CorsFilter();
        return filter;
    }

    @Autowired
    public WebSecurityConfig(final AccessTokenService accessTokenService,
                             final AuthenticationProvider mongoAuthenticationProvider,
                             final RolesRepository rolesRepository) {
        this.accessTokenService = accessTokenService;
        this.mongoAuthenticationProvider = mongoAuthenticationProvider;
        this.rolesRepository = rolesRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authenticationProvider(mongoAuthenticationProvider)
                .authorizeRequests()
                .antMatchers("/api/notallowed").denyAll()
                .and()
                .logout().permitAll()
                .and()
                .addFilterBefore(new AuthenticationTokenProcessingFilter(accessTokenService, rolesRepository), CsrfFilter.class);
    }

    @Override
    public void configure(final WebSecurity web) throws Exception {
        super.configure(web);
        web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**", "/api/login");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(mongoAuthenticationProvider);
    }
}
