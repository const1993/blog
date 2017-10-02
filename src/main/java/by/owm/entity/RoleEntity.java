package by.owm.entity;

import org.springframework.security.core.GrantedAuthority;

import javax.validation.constraints.NotNull;

/**
 * Created by haria on 29.9.17.
 */
public class RoleEntity implements GrantedAuthority {

    private String authority;

    @Override
    public String getAuthority() {
        return authority;
    }

    public void setAuthority(@NotNull final String authority) {
        this.authority = authority;
    }
}
