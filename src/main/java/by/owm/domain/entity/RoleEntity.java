package by.owm.domain.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;

@Data
@Document(collection = "Roles")
public class RoleEntity implements GrantedAuthority {

    private static final long serialVersionUID = 7400150112674192109L;

    @Id
    private String id;
    private String authority;

    public RoleEntity(final String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}
