package ru.yandex.practicum.filmorate.storage.film;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.model.Film;

import java.util.Collection;
import java.util.HashMap;
import java.util.Objects;

@Component
public class InMemoryFilmStorage implements FilmStorage {
    private HashMap<Long, Film> films = new HashMap<>();
    private static final Logger log = LoggerFactory.getLogger(InMemoryFilmStorage.class);

    @Override
    public Film addFilm(Film film) {
        film.setId(getNextId());
        films.put(film.getId(), film);
        return film;
    }

    @Override
    public Collection<Film> getFilms() {
        return this.films.values();
    }


    @Override
    public Film updateFilm(Film film) {
        if (Objects.isNull(film.getId()) || !films.containsKey(film.getId())) {
            log.error("Фильм не найден");
            throw new NotFoundException("Фильм не найден");
        }

        Film currentFilmValue = films.get(film.getId());
        films.put(currentFilmValue.getId(), film);

        return films.get(film.getId());
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
