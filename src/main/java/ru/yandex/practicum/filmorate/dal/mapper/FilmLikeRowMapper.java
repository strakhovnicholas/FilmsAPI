package ru.yandex.practicum.filmorate.dal.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.FilmLike;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class FilmLikeRowMapper implements RowMapper<FilmLike> {
    @Override
    public FilmLike mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        return FilmLike.builder()
                .filmId(resultSet.getLong("film_id"))
                .userId(resultSet.getLong("user_id"))
                .build();
    }
}

