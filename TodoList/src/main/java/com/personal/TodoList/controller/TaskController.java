package com.personal.TodoList.controller;

import com.personal.TodoList.model.Task;
import com.personal.TodoList.service.TaskService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    private Long getUserId(HttpServletRequest request) {
        Object userIdAttr = request.getAttribute("userId");
        if (userIdAttr == null) {
            throw new RuntimeException("Unauthorized");
        }
        return Long.parseLong(userIdAttr.toString());
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAllTask(HttpServletRequest request) {
        Long userId = getUserId(request);
        return ResponseEntity.ok(taskService.getAllTaskByUser(userId));
    }

    @PostMapping("/createTask")
    public ResponseEntity<Task> createTask(@RequestBody Task task,
                                           HttpServletRequest request) {
        Long userId = getUserId(request);
        Task createdTask = taskService.add(task, userId);
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id,
                                            HttpServletRequest request) {
        Long userId = getUserId(request);
        return ResponseEntity.ok(taskService.getTaskById(id, userId));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Task> update(@PathVariable Long id,
                                       @RequestBody Task taskDetails,
                                       HttpServletRequest request) {
        Long userId = getUserId(request);
        return ResponseEntity.ok(taskService.updateTask(id, taskDetails, userId));
    }

    @DeleteMapping("/deleteTask/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id,
                                         HttpServletRequest request) {
        Long userId = getUserId(request);
        taskService.deleteTask(id, userId);
        return ResponseEntity.ok("Task deleted successfully");
    }

    @GetMapping("/getByCompleted")
    public ResponseEntity<List<Task>> getCompletedTask(HttpServletRequest request) {
        Long userId = getUserId(request);
        return ResponseEntity.ok(taskService.getTaskByStatus(true, userId));
    }

    @GetMapping("/getByNotCompleted")
    public ResponseEntity<List<Task>> getNotCompletedTask(HttpServletRequest request) {
        Long userId = getUserId(request);
        return ResponseEntity.ok(taskService.getTaskByStatus(false, userId));
    }

    @PatchMapping("/completed/{taskId}")
    public ResponseEntity<Task> completed(@PathVariable Long taskId,
                                          HttpServletRequest request) {
        Long userId = getUserId(request);
        return ResponseEntity.ok(taskService.markAsCompleted(taskId, userId));
    }

    @PatchMapping("/notCompleted/{taskId}")
    public ResponseEntity<Task> notCompleted(@PathVariable Long taskId,
                                             HttpServletRequest request) {
        Long userId = getUserId(request);
        return ResponseEntity.ok(taskService.markAsIncomplete(taskId, userId));
    }
}