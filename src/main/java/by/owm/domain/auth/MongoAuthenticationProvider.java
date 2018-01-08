package by.owm.domain.auth;

import by.owm.domain.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class MongoAuthenticationProvider implements AuthenticationProvider {

    private final UserService userService;

    @Autowired
    public MongoAuthenticationProvider(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Authentication authenticate(final Authentication authentication) throws AuthenticationException {

        final String name = authentication.getName();
        final Object principal = authentication.getPrincipal();
        final Object credentials = authentication.getCredentials();

        if (!userService.checkIslogIn(name, principal.toString())) {
            return null;
        }

        final UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(principal, credentials, authentication.getAuthorities());
        token.setDetails(authentication.getDetails());

        return token;
    }

    @Override
    public boolean supports(final Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
