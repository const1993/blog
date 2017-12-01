package by.owm.rest.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class TokenDto implements Serializable {

    private static final long serialVersionUID = -3783942855818698793L;

    private String token;
}
