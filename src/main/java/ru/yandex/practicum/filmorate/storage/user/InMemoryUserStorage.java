package ru.yandex.practicum.filmorate.storage.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.User;

import java.util.*;
import java.util.stream.Collectors;

@Component("inMemoryUserStorage")
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
    public User updateUser(User user) {
        if (Objects.isNull(user.getId()) || !users.containsKey(user.getId())) {
            log.error("Пользователь не найден");
            throw new NotFoundException("Пользователь  не найден");
        }

        User currentUser = users.get(user.getId());
        users.put(currentUser.getId(), user);

        return users.get(user.getId());
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


    public Collection<User> addFriend(User user, User friend) throws ValidationException {
        if (user.getId() == friend.getId()) {
            throw new ValidationException("cant be friend with self");
        }
        user.getFriends().add(friend.getId());
        friend.getFriends().add(user.getId());

        return List.of(user, friend);
    }

    @Override
    public Collection<User> addFriend(long userId, long friendId) throws ValidationException {
        User user = getUser(userId);
        User friend = getUser(friendId);

        return addFriend(user, friend);
    }

    public Collection<User> removeFriend(User user, User friend) throws ValidationException {
        user.getFriends().remove(friend.getId());
        friend.getFriends().remove(user.getId());

        return List.of(user, friend);
    }

    public Collection<User> removeFriend(long userId, long friendId) throws ValidationException {
        User user = getUser(userId);
        User friend = getUser(friendId);

        return removeFriend(user, friend);
    }

    public List<User> getUserFriends(User user) {
        return getAllUsers().stream().filter(u -> user.getFriends().contains(u.getId())).collect(Collectors.toList());
    }

    @Override
    public List<User> getUserFriends(long userId) {
        User user = getUser(userId);

        return getUserFriends(user);
    }
    
    public Collection<User> getCommonFriends(User user, User otherUser) {
        Set<User> firstUserFriends = new HashSet<>(getUserFriends(user));
        return getUserFriends(otherUser).stream().filter(firstUserFriends::contains).toList();
    }

    @Override
    public Collection<User> getCommonFriends(long userId, long otherUserId) {
        User user = getUser(userId);
        User otherUser = getUser(otherUserId);

        return getCommonFriends(user, otherUser);
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
