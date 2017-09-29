package by.owm.entity;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by haria on 29.9.17.
 */
public class RoleEntity implements GrantedAuthority{
    @Override
    public String getAuthority() {
        return null;
    }
}
