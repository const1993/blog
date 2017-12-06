package by.owm.domain.repository;

import by.owm.domain.entity.RoleEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;

@Repository
public interface RolesRepository extends MongoRepository<RoleEntity, String> {
    
    RoleEntity findByAuthority(@NotNull final String authority);
}
