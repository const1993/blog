package by.owm.domain.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@AllArgsConstructor
@Document(collection = "Article")
public class Article implements Serializable {

    private static final long serialVersionUID = -5710763257675730777L;
}
