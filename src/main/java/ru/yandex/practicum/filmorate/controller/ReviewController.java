package ru.yandex.practicum.filmorate.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Review;
import ru.yandex.practicum.filmorate.service.ReviewService;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    ReviewService service;

    @Autowired
    public ReviewController(ReviewService service) {
        this.service = service;
    }

    @PostMapping
    public Review create(@RequestBody @Valid Review review) {
        return service.create(review);
    }

    @PutMapping
    public Review update(@RequestBody @Valid Review review) {
        return service.update(review);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/{id}")
    public Review get(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping
    public List<Review> getAll(@RequestParam(required = false) Long filmId, @RequestParam(defaultValue = "10") int count) {
        return service.findAll(filmId, count);
    }

    @PutMapping("/{id}/like/{userId}")
    public void like(@PathVariable Long id, @PathVariable Long userId) {
        service.like(id, userId);
    }

    @PutMapping("/{id}/dislike/{userId}")
    public void dislike(@PathVariable Long id, @PathVariable Long userId) {
        service.dislike(id, userId);
    }

    @DeleteMapping("/{id}/like/{userId}")
    public void removeLike(@PathVariable Long id, @PathVariable Long userId) {
        service.removeReaction(id, userId);
    }

    @DeleteMapping("/{id}/dislike/{userId}")
    public void removeDislike(@PathVariable Long id, @PathVariable Long userId) {
        service.removeReaction(id, userId);
    }
}
