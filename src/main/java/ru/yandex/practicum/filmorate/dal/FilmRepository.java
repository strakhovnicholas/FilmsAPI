package ru.yandex.practicum.filmorate.dal;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.filmorate.model.Film;

import java.util.List;
import java.util.Optional;

@Repository
public class FilmRepository extends BaseRepository<Film> {
    private static final String GET_ALL_FILMS_QUERY = "SELECT * FROM PUBLIC.\"film\"";
    private static final String GET_ONE_FILM_QUERY = "SELECT * FROM PUBLIC.\"film\" WHERE id = ?";
    private static final String ADD_FILM_QUERY = "INSERT INTO PUBLIC.\"film\"\n" +
            "(NAME, DESCRIPTION, RELEASE_DATE, DURATION, MPA_ID)\n" +
            "VALUES(?, ?, ?, ?, ?);";

    public FilmRepository(JdbcTemplate jdbc, RowMapper<Film> mapper) {
        super(jdbc, mapper, Film.class);
    }
    
    public List<Film> getAll() {
        return findMany(GET_ALL_FILMS_QUERY);
    }

    public Optional<Film> getFilm(long id) {
        return findOne(GET_ONE_FILM_QUERY, id);
    }

    public Optional<Film> addFilm(Film film) {
        insert(ADD_FILM_QUERY,
                film.getName(),
                film.getDescription(),
                film.getReleaseDate(),
                film.getDuration(),
                film.getMpaId()
        );
        return getFilm(film.getId());
    }
}
