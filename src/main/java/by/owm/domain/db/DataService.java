package by.owm.domain.db;

import by.owm.domain.db.client.MongoClient;

/**
 * Created by haria on 13.10.2015.
 */
public interface DataService {

    boolean logIn(String name, String password);

    boolean create(String name, String password, String email, String key);

    MongoClient getMongoClient();
}
