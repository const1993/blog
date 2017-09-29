package by.owm.service.acessToken;

import by.owm.entity.AccessTokenEntity;
import by.owm.entity.UserEntity;
import com.sun.istack.internal.Nullable;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

/**
 * Created by haria on 29.9.17.
 */
public interface AccessTokenService {
    /**
     * Get a last access token for the user.
     *
     * @param user the user.
     * @return the last access token or null.
     */
    @Nullable
    AccessTokenEntity getLastToken(@NotNull UserEntity user);

    /**
     * Create a new access token for the user.
     *
     * @param user the user.
     * @return the new access token.
     */
    @NotNull
    AccessTokenEntity createNewToken(@NotNull UserEntity user);

    /**
     * Find an user entity by token value.
     *
     * @param token the token value.
     * @return the user entity or null.
     */
    @Nullable
    UserEntity findUserByToken(@NotNull String token);

}
