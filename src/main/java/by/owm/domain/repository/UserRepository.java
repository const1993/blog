package by.owm.domain.repository;

import by.owm.domain.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    /**
     * Finds user by his email and password.
     *
     * @param email    - users email used as login.
     * @param password - users password, should be encoded.
     * @return User.
     */
    User findUserByEmailAndPassword(final String email, final String password);

    /**
     * Finds user by token generated on registration.
     *
     * @param token - unic token that contains encoded information about user.
     * @return unic token.
     */
    User findUserByToken(final String token);

    boolean existsByNameAndPassword(final String name, final String password);
}
