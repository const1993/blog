package by.owm.domain.auth;

import by.owm.domain.user.UserManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class MongoAuthenticationProvider implements AuthenticationProvider {

    private final UserManagement userService;

    @Autowired
    public MongoAuthenticationProvider(final UserManagement userService) {
        this.userService = userService;
    }

    @Override
    public Authentication authenticate(final Authentication authentication) throws AuthenticationException {

        final String name = authentication.getName();
        final Object principal = authentication.getPrincipal();
        final Object credentials = authentication.getCredentials();

        if (!userService.logIn(name, principal.toString())) {
            return null;
        }

        final UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(principal, credentials, authentication.getAuthorities());
        result.setDetails(authentication.getDetails());

        return result;
    }

    @Override
    public boolean supports(final Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
