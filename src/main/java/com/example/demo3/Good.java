package com.example.demo3;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity(name="good")
@Getter
@Setter
public class Good {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int goodNo;
    String name;
    int type;
    String company;
    Date startDate;
    Date changeDate;
    int stat;
}
