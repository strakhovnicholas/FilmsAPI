package ru.yandex.practicum.filmorate.model;

import lombok.Builder;
import lombok.Data;

@Builder(toBuilder = true)
@Data
public class Mpa {
    private long id;
    private String name;
}
