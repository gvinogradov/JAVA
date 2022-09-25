package main;

import main.core.Task;
import main.core.Task;
import main.core.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class TaskController {

    @GetMapping("/users/{userId}/tasks/{taskId}")
    public ResponseEntity get(@PathVariable long userId, @PathVariable long taskId) {
        if (!userAvailable(userId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        Task task = Storage.getTask(taskId);
        if (task == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return new ResponseEntity(task, HttpStatus.OK);
    }

    @GetMapping("/users/{userId}/tasks/")
    public List<Task> list(@PathVariable long userId) {
        if (!userAvailable(userId)) {
            return Collections.emptyList();
        }
        List<Task> taskList = Storage.getAllTasks();
        return taskList;
    }

    @PostMapping(value = "/users/{userId}/tasks/",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Task> add(@PathVariable long userId, @RequestBody Task task) {
        if (!userAvailable(userId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        task.setUserId(userId);
        Task persistedTask = Storage.addTask(task);
        return ResponseEntity
                .created(URI
                        .create(String.format("/tasks/%d", persistedTask.getId())))
                .body(persistedTask);
    }

    @PostMapping(value = "/users/{userId}/tasks/{taskId}")
    public ResponseEntity<Task> add() {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(null);
    }

    @PutMapping(value = "/users/{userId}/tasks/{taskId}",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Task> update(@PathVariable long userId, @PathVariable long taskId, @RequestBody Task task) {
        System.out.println("put task: " + taskId);
        if (!userAvailable(userId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        task.setId(taskId);
        Task persistedTask = Storage.update(task);
        if (persistedTask == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @PutMapping(value = "/users/{userId}/tasks/",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Task>> updateAll(@PathVariable long userId, @RequestBody List<Task> tasks) {
        if (!userAvailable(userId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        List<Task> persistedTasks = new ArrayList<>();
        for (Task task: tasks) {
            Task persistedTask = Storage.update(task);
            if (persistedTask == null) {
                continue;
            }
            persistedTasks.add(persistedTask);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(persistedTasks);
    }

    @DeleteMapping("/users/{userId}/tasks/{taskId}")
    public ResponseEntity<Task> delete(@PathVariable long userId, @PathVariable long taskId) {
        if (!userAvailable(userId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        Task task = Storage.getTask(taskId);
        if (task == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        Storage.delete(task);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @DeleteMapping("/users/{userId}/tasks/")
    public ResponseEntity<Task> deleteAll(@PathVariable long userId) {
        if (!userAvailable(userId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        Storage.deleteAllTasks();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    private static boolean userAvailable(long userId) {
        return (Storage.getUser(userId) != null);
    }
}
