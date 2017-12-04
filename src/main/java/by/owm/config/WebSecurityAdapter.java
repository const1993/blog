package by.owm.config;

import by.owm.rest.filter.AuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
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
public class WebSecurityAdapter extends WebSecurityConfigurerAdapter {

    private final AuthenticationProvider provider;
    private final AuthenticationFilter filter;

    @Autowired
    public WebSecurityAdapter(final AuthenticationProvider provider,
                              final AuthenticationFilter filter) {
        this.provider = provider;
        this.filter = filter;
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authenticationProvider(provider)
                .authorizeRequests()
                .antMatchers("/api/notallowed").denyAll()
                .and()
                .logout().permitAll()
                .and()
                .addFilterBefore(filter, CsrfFilter.class);
    }

    @Override
    public void configure(final WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**", "/api/login");
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(provider);
    }
}
