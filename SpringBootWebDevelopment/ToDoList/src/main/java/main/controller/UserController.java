package main.controller;

import main.exception.ResourceNotFoundException;
import main.model.User;
import main.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users/{userId}")
    public User get(@PathVariable long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("userId " + userId + " not found"));
    }

    @GetMapping("/users/")
    public List<User> list() {
        return (List<User>) userRepository.findAll();
    }

    @PostMapping(value = "/users/",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<User> add(@RequestBody User user) {
        User persistedUser = (User) userRepository.save(user);
        return ResponseEntity
                .created(URI
                        .create(String.format("/users/%d", persistedUser.getId())))
                .body(persistedUser);
    }

    @PostMapping(value = "/users/{id}")
    public ResponseEntity<User> add() {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(null);
    }

    @PutMapping(value = "/users/{userId}")
    public ResponseEntity<User> update(@PathVariable long userId, @RequestBody User userRequest) {
        User persistedUser = userRepository.findById(userId).map(user -> {
            user.setEmail(userRequest.getEmail());
            user.setFirstName(userRequest.getFirstName());
            user.setLastName(userRequest.getLastName());
            user.setPassword(userRequest.getPassword());
            return (User) userRepository.save(user);
        }).orElseThrow(() -> new ResourceNotFoundException("userId " + userId + " not found"));

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @PutMapping(value = "/users/")
    public ResponseEntity<List<User>> updateAll(@RequestBody List<User> users) {
        List<User> persistedUsers = users.stream()
                .filter(u -> userRepository.existsById(u.getId()))
                .collect(Collectors.toList());
                userRepository.saveAll(persistedUsers);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(persistedUsers);
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<?> delete(@PathVariable long userId) {
        return userRepository.findById(userId).map(user -> {
            userRepository.delete(user);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }).orElseThrow(() -> new ResourceNotFoundException("userId " + userId + " not found"));
    }

    @DeleteMapping("/users/")
    public ResponseEntity<?> deleteAll() {
        userRepository.deleteAll();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
