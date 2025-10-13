package ru.yandex.practicum.filmorate.storage;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import ru.yandex.practicum.filmorate.model.Mpa;
import ru.yandex.practicum.filmorate.storage.mpa.MpaDbStorage;
import ru.yandex.practicum.filmorate.storage.mpa.MpaStorage;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@JdbcTest
@AutoConfigureTestDatabase
@ComponentScan("ru.yandex.practicum.filmorate")
@Import(MpaDbStorage.class)
public class MpaDbStorageTests {
    private final MpaStorage mpaStorage;

    @Autowired
    public MpaDbStorageTests(MpaStorage genreStorage) {
        this.mpaStorage = genreStorage;
    }

    @Test
    void findById() {
        Mpa mpa = mpaStorage.getById(1);
        assertNotNull(mpa.getId());
        assertNotNull(mpa.getName());
    }
}
