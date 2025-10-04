package ru.yandex.practicum.filmorate.storage.mpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.dal.MpaRepository;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.model.Mpa;

import java.util.List;
import java.util.Optional;

@Component
public class MpaDbStorage implements MpaStorage {
    private MpaRepository repository;

    @Autowired
    public MpaDbStorage(MpaRepository repo) {
        this.repository = repo;
    }

    @Override
    public List<Mpa> getAll() {
        return repository.getAll();
    }

    @Override
    public Mpa getById(long id) {
        Optional<Mpa> mpa = repository.getById(id);
        if (mpa.isEmpty()) {
            throw new NotFoundException("mpa not found");
        }
        return mpa.get();
    }
}
