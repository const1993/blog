package by.owm.domain.data;

import by.owm.domain.mongo.MongoClient;

public interface DataService {

    boolean logIn(String name, String password);

    boolean create(String name, String password, String email, String key);

    MongoClient getMongoClient();
}
