package by.owm.service.acessToken.impl;

import by.owm.entity.AccessTokenEntity;
import by.owm.entity.UserEntity;
import by.owm.service.acessToken.AccessTokenService;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.security.SecureRandom;

/**
 * Created by haria on 29.9.17.
 */
@Service
public class AcessTokenServiceImpl implements AccessTokenService {
    protected static SecureRandom random = new SecureRandom();
    @Override
    public AccessTokenEntity getLastToken(@NotNull final UserEntity user) {
        return null;
    }

    @Override
    public AccessTokenEntity createNewToken(@NotNull final UserEntity user) {

        long longToken = Math.abs( random.nextLong() );
        final String random = Long.toString( longToken, 16 );

        return new AccessTokenEntity(random, user.getId());

    }

    @Override
    public UserEntity findUserByToken(@NotNull String token) {
        return null;
    }
}
