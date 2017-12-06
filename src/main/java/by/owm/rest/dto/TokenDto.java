package by.owm.rest.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class TokenDto implements Serializable {

    private static final long serialVersionUID = 4586140991981931668L;

    private String token;
}
