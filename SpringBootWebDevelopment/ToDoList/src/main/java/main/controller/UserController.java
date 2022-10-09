package main.controller;

import main.model.User;
import main.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/{userId}")
    public User get(@PathVariable long userId) {
        return userService.get(userId);
    }

    @GetMapping("/")
    public List<User> getAll() {
        return userService.getAll();
    }

    @PostMapping(value = "/")
    public ResponseEntity<User> add(@RequestBody User user) {
        return userService.add(user);
    }

    @PostMapping(value = "/{id}")
    public ResponseEntity<User> add() {
        return userService.add();
    }

    @PutMapping(value = "/{userId}")
    public ResponseEntity<User> update(@PathVariable long userId, @RequestBody User userRequest) {
        return userService.update(userId, userRequest);
    }

    @PutMapping(value = "/")
    public ResponseEntity<List<User>> updateAll(@RequestBody List<User> users) {
        return userService.updateAll(users);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> delete(@PathVariable long userId) {
        return userService.delete(userId);
    }

    @DeleteMapping("/")
    public ResponseEntity<?> deleteAll() {
       return userService.deleteAll();
    }
}
