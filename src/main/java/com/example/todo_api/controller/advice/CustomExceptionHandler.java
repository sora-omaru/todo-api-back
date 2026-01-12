package com.example.todo_api.controller.advice;

import com.example.todo_api.service.task.TaskEntity;
import com.example.todo_api.service.task.TaskEntityNotFoundException;
import com.example.todoapi.model.ResourceNotFoundError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {


    @ExceptionHandler(TaskEntityNotFoundException.class)
    public ResponseEntity<ResourceNotFoundError> handleTaskEntityNotFoundException(TaskEntityNotFoundException e) {
        var error = new ResourceNotFoundError();
        error.setDetail(e.getMessage());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND).body(error);
    }
}
