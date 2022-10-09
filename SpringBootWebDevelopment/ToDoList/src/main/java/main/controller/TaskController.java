package main.controller;

import main.model.Task;
import main.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/{userId}/tasks/{taskId}")
    public Task get(@PathVariable(value = "userId") long userId, @PathVariable(value = "taskId") long taskId) {
       return taskService.get(userId, taskId);
    }

    @GetMapping("/{userId}/tasks/")
    public List<Task> getAll(@PathVariable long userId) {
        return taskService.getAll(userId);
    }

    @PostMapping(value = "/{userId}/tasks/")
    public ResponseEntity<Task> add(@PathVariable long userId, @RequestBody Task task) {
        return taskService.add(userId, task);
    }

    @PostMapping(value = "/{userId}/tasks/{taskId}")
    public ResponseEntity<Task> add() {
        return taskService.add();
    }

    @PutMapping(value = "/{userId}/tasks/{taskId}")
    public ResponseEntity<Task> update(@PathVariable long userId, @PathVariable long taskId, @RequestBody Task taskRequest) {
        return taskService.update(userId, taskId, taskRequest);
    }

    @PutMapping(value = "/{userId}/tasks/")
    public ResponseEntity<List<Task>> updateAll(@PathVariable long userId, @RequestBody List<Task> tasks) {
        return taskService.updateAll(userId, tasks);
    }

    @DeleteMapping("/{userId}/tasks/{taskId}")
    public ResponseEntity<?> delete(@PathVariable(value = "userId") long userId, @PathVariable(value = "taskId") long taskId) {
       return taskService.delete(userId, taskId);
    }

    @DeleteMapping("/{userId}/tasks/")
    public ResponseEntity<Task> deleteAll(@PathVariable long userId) {
        return taskService.deleteAll(userId);
    }
}
