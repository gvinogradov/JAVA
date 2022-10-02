package main.controller;

import main.exception.ResourceNotFoundException;
import main.model.Task;
import main.model.User;
import main.repository.TaskRepository;
import main.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users/{userId}/tasks/{taskId}")
    public Task get(@PathVariable (value = "userId") long userId, @PathVariable (value = "taskId") long taskId) {
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("userId " + userId + " not found");
        }
        return taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("taskId " + taskId + " not found"));
    }

    @GetMapping("/users/{userId}/tasks/")
    public List<Task> list(@PathVariable long userId) {
        return userRepository.findById(userId)
                .map(user -> user.getTasks())
                .orElseThrow(() -> new ResourceNotFoundException("userId " + userId + " not found"));
    }

    @PostMapping(value = "/users/{userId}/tasks/")
    public ResponseEntity<Task> add(@PathVariable long userId, @RequestBody Task task) {
//        if (!userRepository.existsById(userId)) {
//            throw new ResourceNotFoundException("userId " + userId + " not found");
//        }
//        task.setUserId(userId);
//        Task persistedTask = taskRepository.save(task);

        task.setUserId(userId);
        Task persistedTask =  userRepository.findById(userId).map(user -> {
            task.setUser(user);
            return taskRepository.save(task);
        }).orElseThrow(() -> new ResourceNotFoundException("userId " + userId + " not found"));


        return ResponseEntity
                .created(URI
                        .create(String.format("/users/%d/tasks/%d", userId, persistedTask.getId())))
                .body(persistedTask);
    }

    @PostMapping(value = "/users/{userId}/tasks/{taskId}")
    public ResponseEntity<Task> add() {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(null);
    }

    @PutMapping(value = "/users/{userId}/tasks/{taskId}")
    public ResponseEntity<Task> update(@PathVariable long userId, @PathVariable long taskId, @RequestBody Task taskRequest) {
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("userId " + userId + " not found");
        }
        Task persistedTask =  taskRepository.findById(taskId).map(task -> {
            task.setName(taskRequest.getName());
            task.setDescription(taskRequest.getDescription());
            task.setCreateDate(taskRequest.getCreateDate());
            task.setStart(taskRequest.getStart());
            task.setEnd(taskRequest.getEnd());
            task.setStatus(taskRequest.getStatus());
            task.setStatus(taskRequest.getStatus());
            return taskRepository.save(task);
        }).orElseThrow(() -> new ResourceNotFoundException("taskId " + taskId + " not found"));

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @PutMapping(value = "/users/{userId}/tasks/")
    public ResponseEntity<List<Task>> updateAll(@PathVariable long userId, @RequestBody List<Task> tasks) {
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("userId " + userId + " not found");
        }
        User user = userRepository.findById(userId).get();
        List<Task> persistedTasks = tasks.stream()
                .filter(t -> taskRepository.existsById(t.getId()))
                .collect(Collectors.toList());
        persistedTasks.forEach(task -> {
            task.setUser(user);
            taskRepository.save(task);
        });
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(persistedTasks);
    }

    @DeleteMapping("/users/{userId}/tasks/{taskId}")
    public ResponseEntity<?> delete(@PathVariable (value = "userId") long userId, @PathVariable (value = "taskId") long taskId) {
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("userId " + userId + " not found");
        }
        if (taskRepository.existsById(taskId)) {
            taskRepository.deleteById(taskId);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @DeleteMapping("/users/{userId}/tasks/")
    public ResponseEntity<Task> deleteAll(@PathVariable long userId) {
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("userId " + userId + " not found");
        }
        List<Task> deletedTask = userRepository.findById(userId).get().getTasks();
        taskRepository.deleteAll(deletedTask);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

}
