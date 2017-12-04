package by.owm.rest.filter;

import by.owm.domain.db.UserService;
import by.owm.domain.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static java.util.Collections.unmodifiableCollection;
import static org.apache.commons.lang.StringUtils.isEmpty;
import static org.springframework.security.core.context.SecurityContextHolder.getContext;

@Component
public class AuthenticationFilter extends GenericFilterBean {

    private final UserService service;

    @Autowired
    public AuthenticationFilter(final UserService service) {
        this.service = service;
    }

    @Override
    public void doFilter(final ServletRequest servletRequest,
                         final ServletResponse servletResponse,
                         final FilterChain filterChain) throws IOException, ServletException {

        if (!(servletRequest instanceof HttpServletRequest)) {
            throw new RuntimeException("Expecting an HTTP request");
        }

        final HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;

        final String xAccess = httpRequest.getHeader("X-Access-Token");
        final String accessToken = isEmpty(xAccess) ? httpRequest.getParameter("token") : xAccess;

        final UserEntity userEntity = service.findUserByToken(accessToken);

        if (userEntity != null) {
            getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userEntity, null, unmodifiableCollection(userEntity.getRoles())));
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
