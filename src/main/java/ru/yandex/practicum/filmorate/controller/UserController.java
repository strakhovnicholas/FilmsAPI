package ru.yandex.practicum.filmorate.controller;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.User;
import java.util.Collection;
import java.util.HashMap;
import java.util.Objects;

@RestController
@RequestMapping("/users")
public class UserController {
    private HashMap<Long, User> users = new HashMap<>();
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @GetMapping
    public Collection<User> getAllUsers() {
        return users.values();
    }


    @PostMapping
    public User addUser(@Valid @RequestBody User user) throws ValidationException {
        user.setId(getNextId());
        if (Objects.isNull(user.getName()) || user.getName().isBlank()) {
            user.setName(user.getLogin());
        }
        users.put(user.getId(), user);

        return user;
    }

    @PutMapping
    public User updateUser(@Valid @RequestBody User userForUpdate) {
        if (Objects.isNull(userForUpdate.getId()) || !users.containsKey(userForUpdate.getId())) {
            log.error("Пользователь не найден");
            throw new NotFoundException("Пользователь  не найден");
        }

        User currentUser = users.get(userForUpdate.getId());
        users.put(currentUser.getId(), userForUpdate);

        return users.get(userForUpdate.getId());
    }


    private long getNextId() {
        long currentMaxId = users.keySet()
                .stream()
                .mapToLong(id -> id)
                .max()
                .orElse(0);
        return ++currentMaxId;
    }
}
