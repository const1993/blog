package by.owm.domain.repository;

import by.owm.domain.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<UserEntity, String> {
    /**
     * Finds user by his email and password.
     *
     * @param email    - users email used as login.
     * @param password - users password, should be encoded.
     * @return UserEntity.
     */
    public UserEntity findUserByEmailAndPassword(final String email, final String password);

    /**
     * Finds user by token generated on registration.
     *
     * @param token - unic token that contains encoded information about user.
     * @return unic token.
     */
    public UserEntity findUserByToken(final String token);
}
