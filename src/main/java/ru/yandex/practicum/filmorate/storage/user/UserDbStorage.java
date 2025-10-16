package ru.yandex.practicum.filmorate.storage.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.dal.UserRepository;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.model.User;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Component("userDbStorage")
public class UserDbStorage implements UserStorage {
    private final UserRepository userRepository;

    @Autowired
    public UserDbStorage(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        Optional<User> savedUser = getUser(user.getId());

        if (savedUser.isEmpty()) {
            throw new NotFoundException("Пользователь  не найден");
        }

        return userRepository.update(user);
    }

    @Override
    public Collection<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUser(long id) {
        return userRepository.findOne(id);
    }

    @Override
    public Collection<User> addFriend(long userId, long friendId) {
        Optional<User> user = getUser(userId);
        Optional<User> otherUser = getUser(friendId);

        if (user.isEmpty() || otherUser.isEmpty()) {
            throw new NotFoundException("Пользователь  не найден");
        }

        return userRepository.addFriend(userId, friendId);
    }

    @Override
    public Collection<User> removeFriend(long userId, long friendId) {
        Optional<User> user = getUser(userId);
        Optional<User> otherUser = getUser(friendId);

        if (user.isEmpty() || otherUser.isEmpty()) {
            throw new NotFoundException("Пользователь  не найден");
        }

        return userRepository.removeFriend(userId, friendId);
    }

    @Override
    public List<User> getUserFriends(long userId) {
        Optional<User> savedUser = getUser(userId);

        if (savedUser.isEmpty()) {
            throw new NotFoundException("Пользователь  не найден");
        }
        return userRepository.getFriends(userId);
    }

    @Override
    public Collection<User> getCommonFriends(long userId, long otherUserId) {
        Optional<User> user = getUser(userId);
        Optional<User> otherUser = getUser(otherUserId);

        if (user.isEmpty() || otherUser.isEmpty()) {
            throw new NotFoundException("Пользователь  не найден");
        }

        return userRepository.getCommonFriends(userId, otherUserId);
    }

    @Override
    public void deleteUser(long id) {
        Optional<User> user = getUser(id);
        if (user.isEmpty()) {
            throw new NotFoundException("Пользователь не найден");
        }
        userRepository.delete(id);
    }
}
