package ru.yandex.practicum.filmorate.storage.filmLike;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.dal.FilmLikeRepository;

import java.util.Set;

@Component
public class FilmLikeDbStorage implements FilmLikeStorage {
    private FilmLikeRepository repository;

    @Autowired
    public FilmLikeDbStorage(FilmLikeRepository repo) {
        this.repository = repo;
    }


    @Override
    public void likeFilm(long filmId, long userId) {
        repository.likeFilm(filmId, userId);
    }

    @Override
    public void dislikeFilm(long filmId, long userId) {
        repository.dislikeFilm(filmId, userId);
    }

    @Override
    public Set<Long> getFilmLikes(long filmId) {
        return repository.getFilmLikes(filmId);
    }
}
