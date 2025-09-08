package ru.yandex.practicum.filmorate.controller.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.controller.FilmController;
import ru.yandex.practicum.filmorate.model.Film;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class FilmControllerTest {
    FilmController controller;

    @BeforeEach
    public void beforeEachReset() {
        controller = new FilmController();
    }

    @Test
    public void shouldAddFilm() {
        Film film = Film.builder()
                .id(1)
                .name("test")
                .description("desc")
                .releaseDate(LocalDate.now())
                .duration(5)
                .build();
        controller.addFilm(film);

        assertTrue(controller.getFilms().contains(film));
    }

 @Test
    public void shouldUpdateFilm() {
        Film film = Film.builder()
                .id(1)
                .name("test")
                .description("desc")
                .releaseDate(LocalDate.now())
                .duration(5)
                .build();
        controller.addFilm(film);
        Film updatedFilm = controller.addFilm(film).toBuilder().name("test-2").build();
        controller.updateFilm(updatedFilm);

        assertTrue(controller.getFilms().contains(updatedFilm));
    }
}
