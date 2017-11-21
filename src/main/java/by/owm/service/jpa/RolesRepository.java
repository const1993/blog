package by.owm.service.jpa;

import by.owm.entity.RoleEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import javax.validation.constraints.NotNull;

/**
 * Created by haria on 13.10.17.
 */
public interface RolesRepository extends MongoRepository<RoleEntity, String> {

    public RoleEntity findByAuthority(@NotNull final String authority);
}
