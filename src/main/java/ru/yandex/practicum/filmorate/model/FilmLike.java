package ru.yandex.practicum.filmorate.model;

import lombok.Builder;
import lombok.Data;

@Builder(toBuilder = true)
@Data
public class FilmLike {
    private long userId;
    private long filmId;
}
