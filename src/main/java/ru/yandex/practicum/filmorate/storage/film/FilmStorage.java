package ru.yandex.practicum.filmorate.storage.film;

import ru.yandex.practicum.filmorate.model.Film;

import java.util.Collection;

public interface FilmStorage {
    Film addFilm(Film film);

    Collection<Film> getFilms();

    Film getFilm(long id);

    Film updateFilm(Film film);

    void likeFilm(long filmId, long userId);

    void dislikeFilm(long filmId, long userId);

    Collection<Film> getTopN(int count);

    Collection<Film> getTopN();
}
