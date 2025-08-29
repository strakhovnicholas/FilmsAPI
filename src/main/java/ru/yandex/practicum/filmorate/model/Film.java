package ru.yandex.practicum.filmorate.model;

import jakarta.validation.constraints.*;
import lombok.*;
import ru.yandex.practicum.filmorate.anotations.DateAfter;

import java.time.LocalDate;

@Builder(toBuilder = true)
@Data
public class Film {
    private long id;
    @NotBlank
    @NotNull
    private String name;
    @Size(max = 200)
    private String description;
    @NotNull
    @DateAfter(message = "Дата не может быть ранее чем 28-12-1895",datestring = "28-12-1895")
    private LocalDate releaseDate;
    @NotNull
    @Positive
    private long duration;
}
