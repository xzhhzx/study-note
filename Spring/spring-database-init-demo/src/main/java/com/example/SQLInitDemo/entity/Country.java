package com.example.SQLInitDemo.entity;

import lombok.Data;

import javax.persistence.*;

// 加@Data，使对象能够序列化
@Data
@Entity
public class Country {
    @Id
    private String id;

    @Column
    private String name;
}