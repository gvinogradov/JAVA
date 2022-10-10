package main.service;

import com.sun.istack.NotNull;
import main.model.Task;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TaskService {
    @NotNull
    Task get(@NotNull long userId, @NotNull long taskId);

    @NotNull
    List<Task> getAll(@NotNull long userId);

    @NotNull
    List<Task> getAllTasksAnyUsers();

    @NotNull
    Long getAllTasksAnyUsersCount();

    @NotNull
    ResponseEntity<Task> add(@NotNull long userId, @NotNull Task task);

    @NotNull
    ResponseEntity<Task> add();

    @NotNull
    ResponseEntity<Task> update(@NotNull long userId, @NotNull long taskId, @NotNull Task taskRequest);

    @NotNull
    ResponseEntity<List<Task>> updateAll(@NotNull long userId, @NotNull List<Task> tasks);

    @NotNull
    ResponseEntity<?> delete(@NotNull long userId, @NotNull long taskId);

    @NotNull
    ResponseEntity<Task> deleteAll(@NotNull long userId);
}
