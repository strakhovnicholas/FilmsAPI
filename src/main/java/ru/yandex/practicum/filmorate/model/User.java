package ru.yandex.practicum.filmorate.model;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder(toBuilder = true)
@Data
public class User {
    private long id;
    @NotNull
    @Email
    private String email;
    @NotBlank
    @Pattern(regexp = "^[^\\s]+$")
    private String login;
    private String name;
    @PastOrPresent
    private LocalDate birthday;
}
