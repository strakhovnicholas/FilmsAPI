package ru.yandex.practicum.filmorate.controller;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;

import java.util.Collection;
import java.util.HashMap;
import java.util.Objects;

@RestController
@RequestMapping("/films")
public class FilmController {
    private HashMap<Long, Film> films = new HashMap<>();
    private static final Logger log = LoggerFactory.getLogger(FilmController.class);

    @GetMapping
    public Collection<Film> getFilms() {
        return films.values();
    }

    @PostMapping
    public Film addFilm(@Valid @RequestBody Film film) throws ValidationException {

        film.setId(getNextId());
        films.put(film.getId(), film);

        return film;
    }

    @PutMapping
    public Film updateFilm(@Valid @RequestBody Film filmDataForUpdate) throws ValidationException {
        if (Objects.isNull(filmDataForUpdate.getId()) || !films.containsKey(filmDataForUpdate.getId())) {
            log.error("Фильм не найден");
            throw new NotFoundException("Фильм не найден");
        }

        Film currentFilmValue = films.get(filmDataForUpdate.getId());
        films.put(currentFilmValue.getId(), filmDataForUpdate);

        return films.get(filmDataForUpdate.getId());
    }

    private long getNextId() {
        long currentMaxId = films.keySet()
                .stream()
                .mapToLong(id -> id)
                .max()
                .orElse(0);
        return ++currentMaxId;
    }
}
