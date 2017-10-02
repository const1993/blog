package by.owm.config;

import by.owm.config.filter.AuthenticationTokenProcessingFilter;
import by.owm.service.acessToken.AccessTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfFilter;
import org.tuckey.web.filters.urlrewrite.UrlRewriteFilter;

@Configuration
@EnableWebSecurity
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    CorsFilter corsFilter() {
        CorsFilter filter = new CorsFilter();
        return filter;
    }

    private final AuthenticationProvider mongoAuthenticationProvider;

    private final AccessTokenService accessTokenService;

    @Autowired
    public WebSecurityConfig(final AccessTokenService accessTokenService,
                             AuthenticationProvider mongoAuthenticationProvider) {
        this.accessTokenService = accessTokenService;
        this.mongoAuthenticationProvider = mongoAuthenticationProvider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authenticationProvider(mongoAuthenticationProvider)
                .authorizeRequests()
                .anyRequest().permitAll()
//                .antMatchers("/api/registration").permitAll()
//                .antMatchers("/api/login/**").permitAll()
//                .antMatchers("/images/**").permitAll()
//                .antMatchers("/forum/**").permitAll()
                .antMatchers("/api/notallowed").denyAll()
                .and()
                .logout().permitAll()
                .and()
                .addFilterBefore(new AuthenticationTokenProcessingFilter(accessTokenService), CsrfFilter.class);

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
