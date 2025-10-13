package ru.yandex.practicum.filmorate.storage.filmLike;

import java.util.Set;

public interface FilmLikeStorage {

    void likeFilm(long filmId, long userId);

    void dislikeFilm(long filmId, long userId);

    Set<Long> getFilmLikes(long filmId);
}
