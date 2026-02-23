package com.personal.TodoList.service;

import com.personal.TodoList.exception.ResourceNotFoundException;
import com.personal.TodoList.model.Task;
import com.personal.TodoList.model.User;
import com.personal.TodoList.repositry.TaskRepositry;
import com.personal.TodoList.repositry.UserRepositry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepositry taskRepositry;
    private final UserRepositry userRepository;

    public Task add(Task task, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        task.setUser(user);
        task.setCompleted(false);

        return taskRepositry.save(task);
    }

    public List<Task> getAllTaskByUser(Long userId) {
        return taskRepositry.findByUserId(userId);
    }
    public Task getTaskById(Long taskId, Long userId) {
        System.out.println(taskRepositry.findByUserIdAndCompleted(userId,false));

        return taskRepositry.findByIdAndUserId(taskId, userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Task not found or not owned by user"));
    }

    public Task updateTask(Long taskId, Task taskDetails, Long userId) {
        Task task = getTaskById(taskId, userId);

        task.setTaskName(taskDetails.getTaskName());
        task.setTaskDescription(taskDetails.getTaskDescription());
        task.setCompleted(taskDetails.getCompleted());

        return taskRepositry.save(task);
    }

    public void deleteTask(Long taskId, Long userId) {
        Task task = getTaskById(taskId, userId);
        taskRepositry.delete(task);
    }

    public List<Task> getTaskByStatus(Boolean completed, Long userId) {
        return taskRepositry.findByUserIdAndCompleted(userId, completed);
    }

    public Task markAsCompleted(Long taskId, Long userId) {
        Task task = getTaskById(taskId, userId);
        task.setCompleted(true);
        return taskRepositry.save(task);
    }

    public Task markAsIncomplete(Long taskId, Long userId) {
        Task task = getTaskById(taskId, userId);
        task.setCompleted(false);
        return taskRepositry.save(task);
    }
}