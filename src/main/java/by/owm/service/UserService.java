package by.owm.service;

import by.owm.service.db.client.MongoClient;
import org.bson.BsonDocument;
import org.bson.BsonString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by haria on 05.06.17.
 */
@Service
public class UserService {

    public static final String USERS = "users";

    @Autowired
    private MongoClient mongoClient;

    public boolean logIn(String name, String password)
    {
        final BsonDocument credentials = new BsonDocument().append("name", new BsonString(name))
                .append("password", new BsonString(md5(password)));

        return mongoClient.getCollection(USERS).count(credentials) > 0;
    }

    private String md5(String input) {

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
