package com.example.SQLInitDemo.controller;

import com.example.SQLInitDemo.entity.Country;
import com.example.SQLInitDemo.entity.Country3;
import com.example.SQLInitDemo.mapper.DBMapper;
import org.mybatis.spring.MyBatisSystemException;
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

    // 定义entity类，同时加JPA相关注解 (@Entity等)。注意需要配置spring.jpa.hibernate.ddl-auto=none以防和Hibernate初始化冲突
    @RequestMapping("/list")
    public List<Country> list() {
        return dbMapper.list();
    }

    // 不定义entity类，也可以访问到data.sql中初始化的数据
    @RequestMapping("/list2")
    public List<Map<String, Object>> list2() {
        return dbMapper.list2();
    }

    // 定义entity类，但不加JPA相关注解，也可以访问到data.sql中初始化的数据
    @RequestMapping("/list3")
    public List<Country3> list3() {
        return dbMapper.list3();
    }


    // 延申：MyBatis相关报错
    @RequestMapping("/select2")
    public Map<String, Object> select2(@RequestParam String id) {
        Map<String, Object> res = null;
        try {
            res = dbMapper.select2(id);
        } catch (MyBatisSystemException ex) {
            System.out.println("==== Result should contain ONLY one element =====");
            res = new HashMap<>();
            res.put("return", "too many results");
        }
        // 打印出的异常栈是 TooManyResultsException，但是这是调用MyBatis内部抛出的，实际再抛给调用者的是上面的MyBatisSystemException
//         catch (TooManyResultsException ex) {
//             System.out.println("==== Result should contain only one element =====");
//         }
        return res;
    }
}
