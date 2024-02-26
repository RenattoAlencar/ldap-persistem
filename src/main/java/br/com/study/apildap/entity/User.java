package br.com.study.apildap.entity;

import lombok.Data;

import java.util.UUID;

@Data
public class User {
    private String id;
    private String name;
    private String email;
    private String lastName;


}
