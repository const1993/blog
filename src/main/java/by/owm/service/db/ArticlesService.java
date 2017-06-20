package by.owm.service.db;

import org.bson.Document;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by haria on 05.03.17.
 */
public interface ArticlesService {

    void createOrUpdateArticle(final String id, final String title, final String text);

    void deleteArticle(final String id);

    void putToArchive(final String id);

    List<JSONObject> findArticleByNameAndFilter(final JSONObject filter);

    Document getArticle(final String id);

    void changeActivation(final String id);
}
