package by.owm.domain.user;

import by.owm.domain.entity.RoleEntity;
import by.owm.domain.entity.UserEntity;
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
import static org.springframework.http.ResponseEntity.notFound;
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
    public UserEntity findUserByEmailAndPassword(@NotNull final String email, @NotNull final String password) {
        return userRepository.findUserByEmailAndPassword(email, md5(password));
    }

    @Override
    public UserEntity findUserByToken(@NotNull final String token) {
        return userRepository.findUserByToken(token);
    }

    @Override
    public boolean addNewUser(@NotNull final String name,
                              @NotNull final String surname,
                              @NotNull final String password,
                              @NotNull final String email,
                              @NotNull final List<RoleEntity> roles) {

        return userRepository.save(new UserEntity(name, surname, md5(password), email, roles)) != null;
    }

    @Override
    public boolean updateUser(@NotNull final UserEntity userEntity) {
        return userRepository.save(userEntity) != null;
    }

    @Override
    public boolean logOut(final String token) {

        final UserEntity userEntity = this.findUserByToken(token);

        if (userEntity == null) {
            return true;
        }

        userEntity.setToken(EMPTY);

        return this.updateUser(userEntity);
    }

    @Override
    public boolean checkIslogIn(final String name, final String password) {
        return userRepository.existsByNameAndPassword(name, password);
    }

    @Override
    public UserEntity logIn(String email, String password) {

        long longToken = Math.abs(random.nextLong());
        final String token = Long.toString(longToken, 16);

        final UserEntity userEntity = findUserByEmailAndPassword(email, password);

        if (userEntity == null) {
            throw new IllegalArgumentException("User with this email doesn't exist.");
        }

        userEntity.setToken(token);
        userEntity.setLastLogin(now());

        return userRepository.save(userEntity);
    }

    @Override
    public UserEntity checkToken(String token) {

        final UserEntity userEntity = findUserByToken(token);

        if (userEntity == null) {
            throw new IllegalArgumentException("User with this token doesn't find.");
        }

        final LocalDateTime lastLogin = userEntity.getLastLogin();
        final boolean after = now().minusHours(2).isAfter(lastLogin);
        return after ? userEntity : null;
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
