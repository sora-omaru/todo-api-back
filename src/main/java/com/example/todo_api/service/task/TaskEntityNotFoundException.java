package com.example.todo_api.service.task;

public class TaskEntityNotFoundException extends RuntimeException {
    private long taskId;

    public TaskEntityNotFoundException(long taskId) {
        super("Task with id " + taskId + " not found");
        this.taskId = taskId;
    }
}
