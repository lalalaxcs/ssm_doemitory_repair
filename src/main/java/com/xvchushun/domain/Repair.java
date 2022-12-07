package com.xvchushun.domain;

import lombok.Data;

@Data
public class Repair {
    private int id;
    private String building;
    private String room;
    private int state;
    private Worker worker;
    private Student student;
    private String goods;
}
