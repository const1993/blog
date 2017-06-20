package by.owm.service.db.impl;

import by.owm.service.db.ArticlesService;
import by.owm.service.db.DataService;
import by.owm.service.db.client.MongoClient;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.MongoCollection;
import org.apache.commons.lang.StringUtils;
import org.bson.BsonBoolean;
import org.bson.BsonDocument;
import org.bson.BsonString;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by haria on 05.03.17.
 */
@Service
public class ArticlesServiceImpl implements ArticlesService {

    public static final String ARTICLE = "article";

    @Autowired
    private DataService dataService;

    @Autowired
    private MongoClient mongoClient;

    @Override
    public void createOrUpdateArticle(final String id, final String title, final String text) {

        final MongoCollection collection = getCollection();
        final BsonDocument article = new BsonDocument();
        article.put("title", new BsonString(title));
        article.put("text", new BsonString(text));
        article.put("active", new BsonBoolean(false));

        if (StringUtils.isNotEmpty(id)) {
            final DBObject query = new BasicDBObject("_id", new ObjectId(id));
            final Document foundArticle = getArticle(id);
            if (foundArticle != null) {
                collection.updateOne(foundArticle, article);
                return;
            }
        }

        collection.insertOne(article);
    }

    @Override
    public void deleteArticle(final String id) {
        final MongoCollection collection = getCollection();
        final BsonDocument document = new BsonDocument("_id", new BsonString(id));
        collection.deleteOne(document);
        //TODO: Продумать как проверить реально ли он удалило.
    }

    @Override
    public void putToArchive(String id) {

    }

    @Override
    public List<JSONObject> findArticleByNameAndFilter(final JSONObject object) {


        return null;
    }

    @Override
    public Document getArticle(final String id) {
        return getCollection().find(new BsonDocument("_id", new BsonString(id))).first();
    }

    @Override
    public void changeActivation(final String id) {
        final Document foundArticle = getArticle(id);
        final boolean active = (boolean) foundArticle.get("active");
        final BsonDocument article = new BsonDocument();
        article.put("active", new BsonBoolean(!active));

        getCollection().updateOne(foundArticle, article);
    }

    private MongoCollection<Document> getCollection() {
        return mongoClient.getCollection(ARTICLE);
    }

    public MongoClient getMongoClient() {
        return dataService.getMongoClient();
    }
}
