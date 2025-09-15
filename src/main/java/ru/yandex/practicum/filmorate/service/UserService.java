package ru.yandex.practicum.filmorate.service;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.user.UserStorage;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {
    private UserStorage storage;

    public UserService(UserStorage storage) {
        this.storage = storage;
    }

    public Collection<User> addFriend(long userId, long friendId) throws ValidationException {
        User user = storage.getUser(userId);
        User friend = storage.getUser(friendId);

        return addFriend(user, friend);
    }

    public Collection<User> addFriend(User user, User friend) throws ValidationException {
        if (user.getId() == friend.getId()) {
            throw new ValidationException("cant be friend with self");
        }
        user.getFriends().add(friend.getId());
        friend.getFriends().add(user.getId());

        return List.of(user, friend);
    }

    public Collection<User> removeFriend(long userId, long friendId) throws ValidationException {
        User user = storage.getUser(userId);
        User friend = storage.getUser(friendId);

        return removeFriend(user, friend);
    }

    public Collection<User> removeFriend(User user, User friend) throws ValidationException {
        user.getFriends().remove(friend.getId());
        friend.getFriends().remove(user.getId());

        return List.of(user, friend);
    }

    public List<User> getUserFriends(long userId) {
        User user = storage.getUser(userId);

        return getUserFriends(user);
    }

    public List<User> getUserFriends(User user) {
        return storage.getAllUsers().stream().filter(u -> user.getFriends().contains(u.getId())).collect(Collectors.toList());
    }

    public Collection<User> getCommonFriends(long userId, long otherUserId) {
        User user = storage.getUser(userId);
        User otherUser = storage.getUser(otherUserId);

        return getCommonFriends(user, otherUser);
    }

    public Collection<User> getCommonFriends(User user, User otherUser) {
        Set<User> firstUserFriends = new HashSet<>();
        Collection<User> users = storage.getAllUsers();

        firstUserFriends.addAll(getUserFriends(user));
        return getUserFriends(otherUser).stream().filter((f) -> firstUserFriends.contains(f)).toList();
    }

    public Collection<User> getAllUsers() {
        return storage.getAllUsers();
    }

    public User addUser(@Valid User user) {
        return storage.addUser(user);
    }

    public User updateUser(@Valid User user) {
        return storage.updateUser(user);
    }
}
