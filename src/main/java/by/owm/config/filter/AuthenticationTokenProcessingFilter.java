package by.owm.config.filter;

import by.owm.entity.RoleEntity;
import by.owm.entity.UserEntity;
import by.owm.service.acessToken.AccessTokenService;
import com.sun.istack.internal.Nullable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by haria on 29.9.17.
 */
public class AuthenticationTokenProcessingFilter extends GenericFilterBean {
    @NotNull
    private final AccessTokenService accessTokenService;

    public AuthenticationTokenProcessingFilter(@NotNull final AccessTokenService accessTokenService) {
        this.accessTokenService = accessTokenService;
    }

    @Override
    public void doFilter(@NotNull final ServletRequest servletRequest, @NotNull final ServletResponse servletResponse,
                         @NotNull final FilterChain filterChain) throws IOException, ServletException {

        final HttpServletRequest httpRequest = getAsHttpRequest(servletRequest);
        final String accessToken = extractAuthTokenFromRequest(httpRequest);
        final UserEntity userEntity = accessToken == null ? null : accessTokenService.findUserByToken(accessToken);

        if (userEntity != null) {

            final List<RoleEntity> roles = new ArrayList<>(userEntity.getRoles());
            final UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(userEntity, null, roles);

            final SecurityContext context = SecurityContextHolder.getContext();
            context.setAuthentication(authentication);

        } else {

        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @NotNull
    private HttpServletRequest getAsHttpRequest(@NotNull final ServletRequest request) {

        if (!(request instanceof HttpServletRequest)) {
            throw new RuntimeException("Expecting an HTTP request");
        }

        return (HttpServletRequest) request;
    }

    @Nullable
    private String extractAuthTokenFromRequest(@NotNull final HttpServletRequest httpRequest) {

        /* Get token from header */
        String authToken = httpRequest.getHeader("X-Access-Token");

		/* If token not found get it from request parameter */
        if (authToken == null) {
            authToken = httpRequest.getParameter("token");
        }

        return authToken;
    }
}
