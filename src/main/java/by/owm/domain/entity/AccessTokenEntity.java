package by.owm.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class AccessTokenEntity implements Serializable {

    private static final long serialVersionUID = 5585325453017798997L;

    private String token;
    private String userId;
}
