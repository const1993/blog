package by.owm.domain.db.impl;

import by.owm.domain.db.UserService;
import by.owm.domain.entity.RoleEntity;
import by.owm.domain.entity.UserEntity;
import by.owm.domain.jpa.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import static org.apache.commons.lang.StringUtils.EMPTY;

/**
 * Created by haria on 2.10.17.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserEntity findUserByEmailAndPassword(@NotNull final String email, @NotNull final String password) {
        return userRepository.findUserByEmailAndPassword(email, md5(password));
    }

    @Override
    public UserEntity findUserByToken(@NotNull final String token) {
        return userRepository.findUserByToken(token);
    }

    @Override
    public boolean addNewUser(@NotNull final String name, @NotNull final String surname, @NotNull final String password,
                              @NotNull final String email, @NotNull final List<RoleEntity> roles) {
        final UserEntity userEntity = new UserEntity(name, surname, md5(password), email, roles);
        userRepository.save(userEntity);
        return true;
    }

    @Override
    public boolean updateUser(@NotNull final UserEntity userEntity) {
        userRepository.save(userEntity);
        return true;
    }

    @Override
    public boolean logOut(String token) {

        final UserEntity userEntity = this.findUserByToken(token);

        if (userEntity == null) {
            return true;
        }

        userEntity.setToken(EMPTY);

        return this.updateUser(userEntity);
    }

    @SuppressWarnings("all")
    private String md5(final String input) {

        String md5 = null;

        if (null == input) return null;

        try {

            //Create MessageDigest object for MD5
            MessageDigest digest = MessageDigest.getInstance("MD5");

            //Update input string in message digest
            digest.update(input.getBytes(), 0, input.length());

            //Converts message digest value in base 16 (hex)
            md5 = new BigInteger(1, digest.digest()).toString(16);

        } catch (NoSuchAlgorithmException e) {

            e.printStackTrace();
        }
        return md5;
    }
}
