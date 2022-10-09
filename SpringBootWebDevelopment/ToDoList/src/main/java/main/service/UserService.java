package main.service;

import com.sun.istack.NotNull;
import main.model.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    @NotNull
    User get(@NotNull long userId);

    @NotNull
    List<User> getAll();

    @NotNull
    ResponseEntity<User> add(@NotNull User user);

    @NotNull
    ResponseEntity<User> add();

    @NotNull
    ResponseEntity<User> update(@NotNull long userId, @NotNull User userRequest);

    @NotNull
    ResponseEntity<List<User>> updateAll(@NotNull List<User> users);

    @NotNull
    ResponseEntity<?> delete(@NotNull long userId);

    @NotNull
    ResponseEntity<?> deleteAll();
}
