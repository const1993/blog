package by.owm.service.db.impl;

import by.owm.entity.RoleEntity;
import by.owm.entity.UserEntity;
import by.owm.service.db.UserService;
import by.owm.service.db.client.MongoClient;
import com.mongodb.client.MongoCollection;
import org.bson.BsonDocument;
import org.bson.BsonString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * Created by haria on 2.10.17.
 */
@Service
public class UserServiceImpl implements UserService {

    public static final String USERS = "users";

    @Autowired
    private MongoClient mongoClient;

    @Override
    public UserEntity findUserByEmailAndPassword(@NotNull final String email, @NotNull final String password) {

        final BsonDocument credentials = new BsonDocument().append("email", new BsonString(email))
                .append("password", new BsonString(md5(password)));
        return mongoClient.getCollection(USERS).find(credentials, UserEntity.class).first();
    }

    @Override
    public UserEntity findUserByToken(@NotNull final String token) {
        final BsonDocument credentials = new BsonDocument().append("token", new BsonString(token));
        return mongoClient.getCollection(USERS).find(credentials, UserEntity.class).first();
    }

    @Override
    public boolean addNewUser(@NotNull final String name, @NotNull final String surname, @NotNull final String password,
                           @NotNull final String email, @NotNull final List<RoleEntity> roles) {

        final BsonDocument user = new BsonDocument("name", new BsonString(name)).append("password",
                new BsonString(md5(password)));
        user.put("email", new BsonString(email));
        final MongoCollection collection = mongoClient.getCollection(USERS);
        if(collection.count(user) == 0) {
            final UserEntity userEntity = new UserEntity(name, surname, md5(password), email, roles);
            collection.insertOne(userEntity);
            return true;
        }

        return false;

    }

    private String md5(final String input) {

        String md5 = null;

        if(null == input) return null;

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
