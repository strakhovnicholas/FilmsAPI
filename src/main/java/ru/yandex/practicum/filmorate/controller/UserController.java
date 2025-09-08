package ru.yandex.practicum.filmorate.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.user.UserStorage;

import java.util.Collection;

@RestController
@RequestMapping("/users")
public class UserController {
    UserStorage storage;

    @Autowired
    public UserController(UserStorage storage) {
        this.storage = storage;
    }

    @GetMapping
    public Collection<User> getAllUsers() {
        return storage.getAllUsers();
    }


    @PostMapping
    public User addUser(@Valid @RequestBody User user) throws ValidationException {
        return storage.addUser(user);
    }

    @PutMapping
    public User updateUser(@Valid @RequestBody User userForUpdate) {
        return storage.updateUser(userForUpdate);
    }
}
