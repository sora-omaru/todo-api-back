package com.example.todo_api.controller.sample;

import com.example.todo_api.service.sample.SampleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/samples")
@RequiredArgsConstructor//sampleServiceを受け取るコンストラクタを生成する
public class SampleController {

private final SampleService sampleService;
    //GET/samples
    @GetMapping("")
    public sampleDTO samples() {
        var list = sampleService.find();
        return new sampleDTO(list.getContent(), LocalDateTime.now()) ;
    }
}
