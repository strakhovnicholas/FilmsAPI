package ru.yandex.practicum.filmorate.dal.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

@Component
public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        LocalDate birthday = resultSet.getDate("birthday").toLocalDate();

        return User.builder()
                .id(resultSet.getInt("id"))
                .birthday(birthday)
                .login(resultSet.getString("login"))
                .email(resultSet.getString("email"))
                .build();
    }
}