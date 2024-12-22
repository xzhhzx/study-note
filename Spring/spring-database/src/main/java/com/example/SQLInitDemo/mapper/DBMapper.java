package com.example.SQLInitDemo.mapper;

import com.example.SQLInitDemo.entity.Country;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DBMapper {
    @Select("select * from country")
    List<Country> list();

    @Insert("insert into country values (3, 'Tuvalu'); insert into country values (5, 'Taiwan');")
    int init();

    @Insert("insert into country (name) values (#{name})")
    int insert(String name);

}
