package main.service;

import main.exception.ResourceNotFoundException;
import main.model.User;
import main.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User get(long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("userId " + userId + " not found"));
    }

    @Override
    public List<User> getAll() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public ResponseEntity<User> add(User user) {
        User persistedUser = (User) userRepository.save(user);
        return ResponseEntity
                .created(URI
                        .create(String.format("/users/%d", persistedUser.getId())))
                .body(persistedUser);
    }

    @Override
    public ResponseEntity<User> add() {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(null);
    }

    @Override
    public ResponseEntity<User> update(long userId, User userRequest) {
        User persistedUser = userRepository.findById(userId).map(user -> {
            user.setEmail(userRequest.getEmail());
            user.setFirstName(userRequest.getFirstName());
            user.setLastName(userRequest.getLastName());
            user.setPassword(userRequest.getPassword());
            return (User) userRepository.save(user);
        }).orElseThrow(() -> new ResourceNotFoundException("userId " + userId + " not found"));

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @Override
    public ResponseEntity<List<User>> updateAll(List<User> users) {
        List<User> persistedUsers = users.stream()
                .filter(u -> userRepository.existsById(u.getId()))
                .collect(Collectors.toList());
        userRepository.saveAll(persistedUsers);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(persistedUsers);
    }

    @Override
    public ResponseEntity<?> delete(long userId) {
        return userRepository.findById(userId).map(user -> {
            userRepository.delete(user);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }).orElseThrow(() -> new ResourceNotFoundException("userId " + userId + " not found"));
    }

    @Override
    public ResponseEntity<?> deleteAll() {
        userRepository.deleteAll();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
