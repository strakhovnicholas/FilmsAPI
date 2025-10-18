package ru.yandex.practicum.filmorate.storage.film;

import ru.yandex.practicum.filmorate.model.Film;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface FilmStorage {
    Film addFilm(Film film);

    Collection<Film> getFilms();

    Optional<Film> getFilm(long id);

    Film updateFilm(Film film);

    List<Film> getTopN(int count);

    public List<Film> getFilmsByIds(Collection<Long> ids);
}
