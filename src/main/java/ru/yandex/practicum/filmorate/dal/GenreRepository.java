package ru.yandex.practicum.filmorate.dal;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.filmorate.model.Genre;

import java.util.List;
import java.util.Optional;

@Repository
public class GenreRepository extends BaseRepository<Genre> {
    private static final String GET_ALL_QUERY = "SELECT * FROM PUBLIC.\"genre\"";
    private static final String GET_ONE_QUERY = "SELECT * FROM PUBLIC.\"genre\" WHERE id = ?";

    public GenreRepository(JdbcTemplate jdbc, RowMapper<Genre> mapper) {
        super(jdbc, mapper, Genre.class);
    }

    public List<Genre> getAll() {
        return findMany(GET_ALL_QUERY);
    }

    public Optional<Genre> getById(long id) {
        return findOne(GET_ONE_QUERY, id);
    }

}
