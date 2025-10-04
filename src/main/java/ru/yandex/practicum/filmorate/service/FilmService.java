package ru.yandex.practicum.filmorate.service;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.film.FilmStorage;
import ru.yandex.practicum.filmorate.storage.user.UserStorage;

import java.util.Collection;


@Service
public class FilmService {
    private final FilmStorage filmStorage;
    private final UserStorage userStorage;

    @Autowired
    public FilmService(@Qualifier("FilmDbStorage") FilmStorage filmStorage, @Qualifier("userDbStorage") UserStorage userStorage) {
        this.filmStorage = filmStorage;
        this.userStorage = userStorage;
    }

    public void likeFilm(long filmId, long userId) {
        userStorage.getUser(userId);
        filmStorage.likeFilm(filmId, userId);
    }

    public void dislikeFilm(long filmId, long userId) {
        userStorage.getUser(userId);
        filmStorage.likeFilm(filmId, userId);
    }

    public Collection<Film> getTopN(int count) {
        return filmStorage.getTopN(count);
    }

    public Collection<Film> getTopN() {
        return filmStorage.getTopN();
    }

    public Film updateFilm(@Valid Film film) {
        return filmStorage.updateFilm(film);
    }

    public Collection<Film> getFilms() {
        return filmStorage.getFilms();
    }

    public Film addFilm(@Valid Film film) {
        return filmStorage.addFilm(film);
    }
}
