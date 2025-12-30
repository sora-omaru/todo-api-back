package com.example.todo_api.repository.sample;


import org.springframework.stereotype.Repository;

@Repository
public class SampleRepository {
    public SampleRecord select() {
        return new SampleRecord("レコード、リポジトリの値");
    }
}
