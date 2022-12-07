package com.xvchushun.domain;

import lombok.Data;

@Data
public class User {
    private int id;
    private String account;
    private String password;
    private int type;
}
