package by.owm.domain.user;

import by.owm.domain.db.client.MongoClient;
import org.bson.BsonDocument;
import org.bson.BsonString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static org.springframework.util.StringUtils.isEmpty;

@Service
public class UserService implements UserManagement {

    private final MongoClient mongoClient;

    @Autowired
    public UserService(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    @Override
    public boolean logIn(final String name, final String password) {

        if (isEmpty(name) || isEmpty(password)) {
            throw new ValidationException("Credentials should not be mull or empty.");
        }

        final BsonDocument credentials = new BsonDocument()
                .append("name", new BsonString(name))
                .append("password", new BsonString(md5(password)));

        return mongoClient.getCollection("users").count(credentials) > 0;
    }

    private String md5(final String input) {
        try {

            final MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(input.getBytes(), 0, input.length());

            return new BigInteger(1, digest.digest()).toString(16);

        } catch (final NoSuchAlgorithmException e) {
            throw new IllegalStateException(e);
        }
    }
}
