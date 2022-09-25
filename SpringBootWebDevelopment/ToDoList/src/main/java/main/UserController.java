package main;

import main.core.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class UserController {

    @GetMapping("/users/{userId}")
    public ResponseEntity get(@PathVariable long userId) {
        User user = Storage.getUser(userId);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity(user, HttpStatus.OK);
    }

    @GetMapping("/users/")
    public List<User> list() {
        List<User> userList = Storage.getAllUsers();
        return userList;
    }

    @PostMapping(value = "/users/",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<User> add(@RequestBody User user) {
        User persistedUser = Storage.addUser(user);
        return ResponseEntity
                .created(URI
                        .create(String.format("/users/%d", persistedUser.getId())))
                .body(persistedUser);
    }

    @PostMapping(value = "/users/{id}")
    public ResponseEntity<User> add() {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(null);
    }

    @PutMapping(value = "/users/{userId}",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<User> update(@PathVariable long userId, @RequestBody User user) {
        user.setId(userId);
        User persistedUser = Storage.update(user);
        if (persistedUser == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @PutMapping(value = "/users/",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> updateAll(@RequestBody List<User> users) {
        List<User> persistedUsers = new ArrayList<>();
        for (User user: users) {
            User persistedUser = Storage.update(user);
            if (persistedUser == null) {
                continue;
            }
            persistedUsers.add(persistedUser);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(persistedUsers);
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<User> delete(@PathVariable long userId) {
        User user = Storage.getUser(userId);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        Storage.delete(user);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @DeleteMapping("/users/")
    public ResponseEntity<User> deleteAll() {
        Storage.deleteAllUsers();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
