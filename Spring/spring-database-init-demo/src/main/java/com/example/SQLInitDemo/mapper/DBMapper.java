package com.example.SQLInitDemo.mapper;

import com.example.SQLInitDemo.entity.Country;
import com.example.SQLInitDemo.entity.Country3;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface DBMapper {
    @Select("select * from country")
    List<Country> list();

    @Select("select * from country2")
    List<Map<String, Object>> list2();

    @Select("select * from country2 where id = #{id}")
    Map<String, Object> select2(@Param("id") String id);

    @Select("select * from country3")
    List<Country3> list3();
}
