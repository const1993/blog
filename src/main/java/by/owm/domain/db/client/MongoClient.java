package by.owm.domain.db.client;

import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.apache.log4j.Logger;
import org.bson.Document;
import org.springframework.stereotype.Service;

import java.net.UnknownHostException;
import java.util.Arrays;

@Service
public class MongoClient {

    private final Logger logger = Logger.getLogger(MongoClient.class);

    private String host;
    private String port;
    private String dbName;
    private String userName;
    private String password;

    private MongoDatabase database;
    private MongoCredential mongoCredential;

    public MongoClient() throws UnknownHostException
    {
        super();
    }

    public MongoCollection<Document> getCollection(String collectionName)
    {
        return getDatabase().getCollection(collectionName);
    }

    public String getHost()
    {
        return host;
    }

    public void setHost(String host)
    {
        this.host = host;
    }

    public String getPort()
    {
        return port;
    }

    public void setPort(String port)
    {
        this.port = port;
    }

    public String getDbName()
    {
        return dbName;
    }

    public void setDbName(String dbName)
    {
        this.dbName = dbName;
    }

    public MongoDatabase getDatabase()
    {
        if (database == null)
        {
            com.mongodb.MongoClient mongo;
            try
            {
                mongoCredential = MongoCredential.createCredential(userName, getDbName(), password.toCharArray());
                mongo = new com.mongodb.MongoClient(new ServerAddress(getHost(), Integer.valueOf(getPort())),
                        Arrays.asList(mongoCredential));
                database = mongo.getDatabase(getDbName());
            }
            catch (NumberFormatException e)
            {
                logger.error(e.getMessage());
            }

        }
        return database;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
}
