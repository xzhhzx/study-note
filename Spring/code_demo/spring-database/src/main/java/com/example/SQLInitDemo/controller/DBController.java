package com.example.SQLInitDemo.controller;

import com.example.SQLInitDemo.entity.Country;
import com.example.SQLInitDemo.mapper.DBMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

// 实例：使用schema.sql和data.sql初始化数据库
@RestController
@RequestMapping("/de")
public class DBController {

    @Autowired
    DBMapper dbMapper;

    // 定义entity类，同时加JPA相关注解。注意需要配置spring.jpa.hibernate.ddl-auto=none以防和Hibernate初始化冲突
    @RequestMapping("/list")
    public List<Country> list() {
        return dbMapper.list();
    }

    @RequestMapping("/init")
    public int init() {
        return dbMapper.init();
    }

    @RequestMapping("/insert")
    // Insert a random country name
    public int insert() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            sb.append((char) Math.round(Math.random() * 93 + 33));
        }
        return dbMapper.insert(sb.toString());
    }
}
