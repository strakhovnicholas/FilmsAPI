package ru.yandex.practicum.filmorate.dal;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.filmorate.model.Mpa;

import java.util.List;
import java.util.Optional;

@Repository
public class MpaRepository extends BaseRepository<Mpa> {
    private static final String GET_ALL_QUERY = "SELECT * FROM PUBLIC.\"mpa\"";
    private static final String GET_ONE_QUERY = "SELECT * FROM PUBLIC.\"mpa\" WHERE id = ?";

    public MpaRepository(JdbcTemplate jdbc, RowMapper<Mpa> mapper) {
        super(jdbc, mapper, Mpa.class);
    }

    public List<Mpa> getAll() {
        return findMany(GET_ALL_QUERY);
    }

    public Optional<Mpa> getById(long id) {
        return findOne(GET_ONE_QUERY, id);
    }

}
