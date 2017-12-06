package by.owm.domain.user;

import by.owm.domain.model.Role;
import by.owm.domain.model.User;

import java.util.List;

public interface UserService {

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

    /**
     * Adds a new user in system.
     *
     * @param name     - Users name.
     * @param surname  - Users surname.
     * @param password - Users not encoded password.
     * @param email    - Users email.
     * @param roles    - Roles added to user.
     * @return success flag.
     */
    boolean addNewUser(final String name, final String surname, final String password, final String email,
                       final List<Role> roles);

    boolean updateUser(final User user);

    boolean logOut(String token);

    boolean checkIslogIn(String name, String password);

    User logIn(String email, String password);

    User checkToken(String token);
}
