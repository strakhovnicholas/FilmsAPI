package ru.yandex.practicum.filmorate.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.film.FilmStorage;

import java.util.Collection;

@RestController
@RequestMapping("/films")
public class FilmController {
    private final FilmStorage storage;

    @Autowired
    public FilmController(FilmStorage storage) {
        this.storage = storage;
    }

    @GetMapping
    public Collection<Film> getFilms() {
        return storage.getFilms();
    }

    @PostMapping
    public Film addFilm(@Valid @RequestBody Film film) {
        return storage.addFilm(film);
    }

    @PutMapping
    public Film updateFilm(@Valid @RequestBody Film film) throws ValidationException {
        return storage.updateFilm(film);
    }
}
