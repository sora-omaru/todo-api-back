package com.example.todo_api.controller.advice;

import com.example.todoapi.model.BadRequestError;
import com.example.todoapi.model.InvalidParam;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;
import java.util.stream.Collectors;

public class BadRequestErrorCreator {

    public static BadRequestError from(MethodArgumentNotValidException ex) {
        var invalidParaList = createInvalidParamList(ex);
        ;//フィールドで発生したエラー取ってくることができる

        var error = new BadRequestError();
        error.setInvalidParams(invalidParaList);
        return error;
    }

    private static List<InvalidParam> createInvalidParamList(MethodArgumentNotValidException ex) {
        return ex.getFieldErrors().stream().map(BadRequestErrorCreator::createInvalidParam).collect(Collectors.toList());
    }

    private static InvalidParam createInvalidParam(FieldError fieldError) {
        var invalidParam = new InvalidParam();
        invalidParam.setName(fieldError.getField());
        invalidParam.setReason(fieldError.getDefaultMessage());
        return invalidParam;
    }
}
