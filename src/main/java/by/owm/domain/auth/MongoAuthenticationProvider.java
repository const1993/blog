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

    @Autowired
    private UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        final String name = authentication.getName();
        final Object principal = authentication.getPrincipal();
        Object credentials = authentication.getCredentials();
        boolean isExist = userService.logIn(name, principal.toString());
        if (!isExist) {
            return null;
        }
        final UsernamePasswordAuthenticationToken result =
                new UsernamePasswordAuthenticationToken(principal, credentials, authentication.getAuthorities());
        result.setDetails(authentication.getDetails());
        return result;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }
}
