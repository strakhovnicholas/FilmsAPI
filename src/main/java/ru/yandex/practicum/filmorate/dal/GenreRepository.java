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
    private static final String GET_FILM_GENRE = "SELECT *\n" +
            "FROM PUBLIC.\"genre\" g \n" +
            "WHERE g.id IN (\n" +
            "SELECT fg.GENRE_ID  \n" +
            "FROM PUBLIC.\"film_genre\" fg \n" +
            "WHERE fg.FILM_ID   = ?)";
    private static final String ADD_FILM_GENRE = "INSERT INTO PUBLIC.\"film_genre\"\n" +
            "(FILM_ID, GENRE_ID)\n" +
            "VALUES(?, ?);";

    public GenreRepository(JdbcTemplate jdbc, RowMapper<Genre> mapper) {
        super(jdbc, mapper, Genre.class);
    }

    public List<Genre> getAll() {
        return findMany(GET_ALL_QUERY);
    }

    public Optional<Genre> getById(long id) {
        return findOne(GET_ONE_QUERY, id);
    }

    public List<Genre> getFilmGenre(long id) {
        return findMany(GET_FILM_GENRE, id);
    }

    public void addFilmGenre(long film_id, long genre_id) {
        insertNoKey(ADD_FILM_GENRE, film_id, genre_id);
    }
}
