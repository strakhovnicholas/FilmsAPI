package ru.yandex.practicum.filmorate.service;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.film.FilmStorage;
import ru.yandex.practicum.filmorate.storage.user.UserStorage;

import java.util.Collection;


@Service
public class FilmService {
    private FilmStorage filmStorage;
    private UserStorage userStorage;

    @Autowired

    public FilmService(FilmStorage filmStorage, UserStorage userStorage) {
        this.filmStorage = filmStorage;
        this.userStorage = userStorage;
    }

    public void likeFilm(long filmId, long userId) {
        Film film = filmStorage.getFilm(filmId);
        User user = userStorage.getUser(userId);

        film.getLikes().add(user.getId());
    }

    public void dislikeFilm(long filmId, long userId) {
        Film film = filmStorage.getFilm(filmId);
        User user = userStorage.getUser(userId);

        film.getLikes().remove(userId);
    }

    public Collection<Film> getTopN(int count) {
        Collection<Film> films = filmStorage.getFilms();
        return films.stream()
                .sorted((a, b) -> b.getLikes().size() - a.getLikes().size())
                .toList().subList(0, Math.min(films.size(), count));
    }

    public Collection<Film> getTopN() {
        return getTopN(10);
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
