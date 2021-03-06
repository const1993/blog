package by.owm.domain.repository;

import by.owm.domain.model.Article;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ArticleRepository extends MongoRepository<Article, String> {
}
