package by.owm.service.db.impl;

import by.owm.service.db.DataService;
import by.owm.service.db.client.MongoClient;
import com.mongodb.client.MongoCollection;
import org.bson.BsonBoolean;
import org.bson.BsonDocument;
import org.bson.BsonString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class DataServiceImpl implements DataService {
    public static final String USERS = "users";

    @Autowired
    private MongoClient mongoClient;

    public boolean logIn(String name, String password)
    {
        final BsonDocument credentials = new BsonDocument("name", new BsonString(name))
                .append("password", new BsonString(md5(password)));

        return mongoClient.getCollection(USERS).count(credentials) > 0;
    }

    public boolean create(final String name, final String password, final String emaail, final  String key) {

        final BsonDocument user = new BsonDocument("name", new BsonString(name)).append("password",
                new BsonString(md5(password)));
        user.put("email", new BsonString(emaail));
        final MongoCollection collection = mongoClient.getCollection(USERS);
        if(collection.count(user) == 0) {
            user.put("active", new BsonBoolean(false));
            user.put("act_key", new BsonString(key));
            collection.insertOne(user);
            return true;
        }

        return false;
    }


    public MongoClient getMongoClient() {
        return mongoClient;
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
