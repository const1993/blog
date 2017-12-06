package by.owm.config;

import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import static com.mongodb.MongoCredential.createCredential;
import static java.util.Collections.singletonList;

@Configuration
@EnableMongoRepositories(basePackages = "by.owm.domain.repository")
public class MongoConfiguration {

    @Bean
    public MongoDbFactory mongoDbFactory(@Value("${mongo.host}") final String host,
                                         @Value("${mongo.port}") final Integer port,

                                         @Value("${mongo.db.name}") final String dbName,

                                         @Value("${mongo.user.name}") final String account,
                                         @Value("${mongo.user.password}") final String password) throws Exception {

        final MongoClient mongoClient = new MongoClient(
                new ServerAddress(host, port),
                singletonList(createCredential(account, dbName, password.toCharArray()))
        );

        return new SimpleMongoDbFactory(mongoClient, dbName);
    }

    @Bean
    public MongoTemplate mongoTemplate(final MongoDbFactory factory) throws Exception {
        return new MongoTemplate(factory);
    }
}
