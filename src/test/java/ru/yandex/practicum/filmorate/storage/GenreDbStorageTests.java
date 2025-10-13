package ru.yandex.practicum.filmorate.storage;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import ru.yandex.practicum.filmorate.model.Genre;
import ru.yandex.practicum.filmorate.storage.genre.GenreDbStorage;
import ru.yandex.practicum.filmorate.storage.genre.GenreStorage;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@JdbcTest
@AutoConfigureTestDatabase
@ComponentScan("ru.yandex.practicum.filmorate")
@Import(GenreDbStorage.class)
public class GenreDbStorageTests {
    private final GenreStorage genreStorage;

    @Autowired
    public GenreDbStorageTests(GenreStorage genreStorage) {
        this.genreStorage = genreStorage;
    }

    @Test
    void findById() {
        Genre genre = genreStorage.getById(1);
        assertNotNull(genre.getId());
        assertNotNull(genre.getName());
    }
}
