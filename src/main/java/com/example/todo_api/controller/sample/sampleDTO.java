package com.example.todo_api.controller.sample;

import lombok.Data;
import lombok.Value;

import java.time.LocalDateTime;
@Data
@Value
public class sampleDTO {

     String content;
     LocalDateTime timestamp;

//    public sampleDTO(String content, LocalDateTime timestamp) {
//        this.content = content;
//        this.timestamp = timestamp;
//    }
//
//    public String getContent() {
//        return content;
//    }
//
//    public LocalDateTime getTimestamp() {
//        return timestamp;
//    }

}
