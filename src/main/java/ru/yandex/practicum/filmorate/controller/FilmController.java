package ru.yandex.practicum.filmorate.controller;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
        try {
            checkIfFilmIsValid(film);
        } catch (ValidationException e) {
            log.error(e.getMessage());
            throw e;
        }

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

        try {
            checkIfFilmIsValid(filmDataForUpdate);
        } catch (ValidationException e) {
            log.error(e.getMessage());
            throw e;
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

    private void checkIfFilmIsValid(Film film) throws ValidationException {
        if (Objects.isNull(film.getName()) || film.getName().isBlank()) {
            throw new ValidationException("Название не может быть пустым");
        }
        if (Objects.isNull(film.getDescription()) || film.getDescription().length() > 200) {
            throw new ValidationException("Описание больше 200 символов");
        }
        if (Objects.isNull(film.getReleaseDate()) || film.getReleaseDate().isBefore(LocalDate.parse("28-12-1895", DateTimeFormatter.ofPattern("dd-MM-yyyy")))) {
            throw new ValidationException("Дата не может быть ранее чем 28-12-1895");
        }
        if (Objects.isNull(film.getDuration()) || film.getDuration() < 0) {
            throw new ValidationException("Продолжительность не может быть отрицательным числом");
        }
    }
}
