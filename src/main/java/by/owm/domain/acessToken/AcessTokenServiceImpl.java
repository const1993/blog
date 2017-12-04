package by.owm.domain.acessToken;

import by.owm.domain.entity.AccessTokenEntity;
import by.owm.domain.entity.UserEntity;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.security.SecureRandom;

@Service
public class AcessTokenServiceImpl implements AccessTokenService {

    private static SecureRandom random = new SecureRandom();

    @Override
    public AccessTokenEntity getLastToken(@NotNull final UserEntity user) {
        return null;
    }

    @Override
    public AccessTokenEntity createNewToken(@NotNull final UserEntity user) {

        long longToken = Math.abs(random.nextLong());
        final String random = Long.toString(longToken, 16);

        return new AccessTokenEntity(random, user.getId());

    }

    @Override
    public UserEntity findUserByToken(@NotNull String token) {
        return null;
    }
}
