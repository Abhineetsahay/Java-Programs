package com.personal.TodoList.repositry;

import com.personal.TodoList.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepositry extends JpaRepository<Task, Long> {

    List<Task> findByUserId(Long userId);

    List<Task> findByUserIdAndCompleted(Long userId, Boolean completed);
    Optional<Task> findByIdAndUserId(Long id, Long userId);
}