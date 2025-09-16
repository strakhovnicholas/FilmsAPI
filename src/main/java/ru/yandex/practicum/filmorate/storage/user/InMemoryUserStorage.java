package ru.yandex.practicum.filmorate.storage.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.model.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.Objects;

@Component
public class InMemoryUserStorage implements UserStorage {
    private HashMap<Long, User> users = new HashMap<>();
    private static final Logger log = LoggerFactory.getLogger(InMemoryUserStorage.class);

    @Override
    public User addUser(User user) {
        user.setId(getNextId());
        if (Objects.isNull(user.getName()) || user.getName().isBlank()) {
            user.setName(user.getLogin());
        }
        users.put(user.getId(), user);
        return user;
    }

    @Override
    public User updateUser(User userForUpdate) {
        if (Objects.isNull(userForUpdate.getId()) || !users.containsKey(userForUpdate.getId())) {
            log.error("Пользователь не найден");
            throw new NotFoundException("Пользователь  не найден");
        }

        User currentUser = users.get(userForUpdate.getId());
        users.put(currentUser.getId(), userForUpdate);

        return users.get(userForUpdate.getId());
    }

    @Override
    public Collection<User> getAllUsers() {
        return users.values();
    }

    @Override
    public User getUser(long id) {
        if (!users.containsKey(id)) {
            log.error("Пользователь не найден");
            throw new NotFoundException("Пользователь  не найден");
        }

        return users.get(id);
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
