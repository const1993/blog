package by.owm.service.acessToken.impl;

import by.owm.entity.AccessTokenEntity;
import by.owm.entity.UserEntity;
import by.owm.service.acessToken.AccessTokenService;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

/**
 * Created by haria on 29.9.17.
 */
@Service
public class AcessTokenServiceImpl implements AccessTokenService {
    @Override
    public AccessTokenEntity getLastToken(@NotNull UserEntity user) {
        return null;
    }

    @Override
    public AccessTokenEntity createNewToken(@NotNull UserEntity user) {


        return null;
    }

    @Override
    public UserEntity findUserByToken(@NotNull String token) {
        return null;
    }
}
