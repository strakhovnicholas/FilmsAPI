package ru.yandex.practicum.filmorate.storage.user;

import ru.yandex.practicum.filmorate.model.User;

import java.util.Collection;
import java.util.List;

public interface UserStorage {
    User addUser(User user);

    User updateUser(User user);

    Collection<User> getAllUsers();

    User getUser(long id);

    Collection<User> addFriend(long userId, long friendId);

    Collection<User> removeFriend(long userId, long friendId);

    List<User> getUserFriends(long userId);

    Collection<User> getCommonFriends(long userId, long otherUserId);

}
