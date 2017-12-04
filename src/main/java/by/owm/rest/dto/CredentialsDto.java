package by.owm.rest.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CredentialsDto implements Serializable {

    private static final long serialVersionUID = 1253532638904198659L;

    private String email;
    private String password;
}
