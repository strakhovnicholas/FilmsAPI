package ru.yandex.practicum.filmorate.storage.film;

import ru.yandex.practicum.filmorate.model.Film;

import java.util.Collection;
import java.util.List;

public interface FilmStorage {
    Film addFilm(Film film);

    Collection<Film> getFilms();

    Film getFilm(long id);

    Film updateFilm(Film film);

    List<Film> getTopN(int count);
}
