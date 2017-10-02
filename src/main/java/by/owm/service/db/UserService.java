package by.owm.service.db;

import by.owm.entity.RoleEntity;
import by.owm.entity.UserEntity;

import java.util.List;

/**
 * Created by haria on 2.10.17.
 */
public interface UserService {


    /**
     * Finds user by his email and password.
     * @param email - users email used as login.
     * @param password - users password, should be encoded.
     * @return UserEntity.
     */
    public UserEntity findUserByEmailAndPassword(final String email, final String password);

    /**
     * Finds user by token generated on registration.
     * @param token - unic token that contains encoded information about user.
     * @return unic token.
     */
    public UserEntity findUserByToken(final String token);

    /**
     * Adds a new user in system.
     * @param name - Users name.
     * @param surname - Users surname.
     * @param password - Users not encoded password.
     * @param email - Users email.
     * @param roles - Roles added to user.
     * @return success flag.
     */
    public boolean addNewUser(final String name, final String surname, final String password, final String email,
                           final List<RoleEntity> roles);

}
