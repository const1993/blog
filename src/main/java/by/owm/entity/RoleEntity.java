package by.owm.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;

import javax.validation.constraints.NotNull;

/**
 * Created by haria on 29.9.17.
 */
@Document(collection = "Roles")
public class RoleEntity implements GrantedAuthority {

    @Id
    private String id;

    private String authority;

    public RoleEntity(final String authority){
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }

    public void setAuthority(@NotNull final String authority) {
        this.authority = authority;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
