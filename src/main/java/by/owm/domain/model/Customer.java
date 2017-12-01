package by.owm.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class Customer implements Serializable {

    private static final long serialVersionUID = -5940940837406623770L;

    private int id;
    private String firstname;
    private String lastname;
    private int age;
}
