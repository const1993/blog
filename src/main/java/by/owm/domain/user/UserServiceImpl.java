package by.owm.domain.user;

import by.owm.domain.model.Role;
import by.owm.domain.model.User;
import by.owm.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;

import static java.time.LocalDateTime.now;
import static org.apache.commons.lang.StringUtils.EMPTY;
import static org.springframework.util.StringUtils.isEmpty;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final SecureRandom random;

    @Autowired
    public UserServiceImpl(final UserRepository userRepository) {
        this.userRepository = userRepository;
        this.random = new SecureRandom();
    }

    @Override
    public User findUserByEmailAndPassword(@NotNull final String email, @NotNull final String password) {
        return userRepository.findUserByEmailAndPassword(email, md5(password));
    }

    @Override
    public User findUserByToken(@NotNull final String token) {
        return userRepository.findUserByToken(token);
    }

    @Override
    public boolean addNewUser(@NotNull final String name,
                              @NotNull final String surname,
                              @NotNull final String password,
                              @NotNull final String email,
                              @NotNull final List<Role> roles) {

        return userRepository.save(new User(name, surname, md5(password), email, roles)) != null;
    }

    @Override
    public boolean updateUser(@NotNull final User user) {
        return userRepository.save(user) != null;
    }

    @Override
    public boolean logOut(final String token) {

        final User user = this.findUserByToken(token);

        if (user == null) {
            return true;
        }

        user.setToken(EMPTY);

        return this.updateUser(user);
    }

    @Override
    public boolean checkIslogIn(final String name, final String password) {
        return userRepository.existsByNameAndPassword(name, password);
    }

    @Override
    public User logIn(String email, String password) {

        long longToken = Math.abs(random.nextLong());
        final String token = Long.toString(longToken, 16);

        final User user = findUserByEmailAndPassword(email, password);

        if (user == null) {
            throw new IllegalArgumentException("User with this email doesn't exist.");
        }

        user.setToken(token);
        user.setLastLogin(now());

        return userRepository.save(user);
    }

    @Override
    public User checkToken(String token) {

        final User user = findUserByToken(token);

        if (user == null) {
            throw new IllegalArgumentException("User with this token doesn't find.");
        }

        final LocalDateTime lastLogin = user.getLastLogin();
        final boolean after = now().minusHours(2).isAfter(lastLogin);
        return after ? user : null;
    }

    private String md5(final String input) {

        if (isEmpty(input)) {
            throw new IllegalArgumentException("Imput data shoud not be null or empty.");
        }

        try {

            final MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(input.getBytes(), 0, input.length());

            return new BigInteger(1, digest.digest()).toString(16);

        } catch (final NoSuchAlgorithmException e) {
            throw new IllegalStateException(e);
        }
    }
}
