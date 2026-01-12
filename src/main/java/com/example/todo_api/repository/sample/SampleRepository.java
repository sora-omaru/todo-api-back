package com.example.todo_api.repository.sample;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SampleRepository {
    @Select("SELECT content FROM sample ORDER BY id LIMIT 1")
    SampleRecord select();
}
