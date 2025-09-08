package ru.yandex.practicum.filmorate.controller.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.controller.UserController;
import ru.yandex.practicum.filmorate.model.User;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class UserControllerTest {
    UserController controller;

    @BeforeEach
    public void beforeEachReset() {
        controller = new UserController();
    }

    @Test
    public void shouldAddUser() {
        User user = User.builder()
                .id(1)
                .name("test")
                .login("testlogin")
                .email("test@test.ru")
                .birthday(LocalDate.now().minusDays(5))
                .build();
        controller.addUser(user);

        assertTrue(controller.getAllUsers().contains(user));
    }

    @Test
    public void shouldUpdateFilm() {
        User user = User.builder()
                .id(1)
                .name("test")
                .login("testlogin")
                .email("test@test.ru")
                .birthday(LocalDate.now().minusDays(5))
                .build();
        controller.addUser(user);
        User updatedUser = controller.addUser(user).toBuilder().name("test-2").build();
        controller.updateUser(updatedUser);

        assertTrue(controller.getAllUsers().contains(user));
    }
}
