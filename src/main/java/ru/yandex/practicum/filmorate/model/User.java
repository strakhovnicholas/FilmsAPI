package ru.yandex.practicum.filmorate.model;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@Builder(toBuilder = true)
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
    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    @Builder.Default()
    private Set<Long> friends = new HashSet<>();


    public Set<Long> getFriends() {
        if (Objects.isNull(friends)) {
            friends = new HashSet<>();
        }
        return friends;
    }
}
