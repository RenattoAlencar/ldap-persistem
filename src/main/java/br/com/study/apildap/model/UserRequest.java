package br.com.study.apildap.model;

import lombok.Data;

@Data
public class UserRequest {
    private String name;
    private String email;
    private String lastName;
}
