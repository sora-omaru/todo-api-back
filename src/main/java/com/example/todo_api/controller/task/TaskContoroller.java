package com.example.todo_api.controller.task;

import com.example.todo_api.service.task.TaskService;
import com.example.todoapi.controller.TasksApi;
import com.example.todoapi.model.TaskDTO;
import com.example.todoapi.model.TaskForm;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class TaskContoroller implements TasksApi {
    private final TaskService taskService;

    @Override
    public ResponseEntity<TaskDTO> showTask(Long taskId) {
//        return TasksApi.super.tasks1Get();
        var entity = taskService.find(taskId);

        var dto = new TaskDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        return ResponseEntity.ok(dto);
    }

    //taskを作成するAPI
    @Override
    public ResponseEntity<TaskDTO> createTask(TaskForm taskForm) {
        var entity = taskService.create(taskForm.getTitle());
        var dto = new TaskDTO();

        dto.setTitle(entity.getTitle());
        dto.setId(entity.getId());
        return ResponseEntity.created(URI.create("/tasks/" + dto.getId())).body(dto);
    }
}
