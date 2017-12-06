package by.owm.domain.repository;

import by.owm.domain.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import javax.validation.constraints.NotNull;

public interface RolesRepository extends MongoRepository<Role, String> {

    Role findByAuthority(@NotNull final String authority);
}
