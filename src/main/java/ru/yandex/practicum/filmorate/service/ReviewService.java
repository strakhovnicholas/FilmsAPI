package ru.yandex.practicum.filmorate.service;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.model.Review;
import ru.yandex.practicum.filmorate.storage.review.ReviewStorage;

import java.util.List;

@Service
public class ReviewService {
    private final ReviewStorage storage;

    public ReviewService(@Qualifier("reviewDbStorage") ReviewStorage storage) {
        this.storage = storage;
    }

    public Review create(@Valid Review review) {
        return storage.create(review);
    }

    public Review update(Review review) {
        return storage.update(review);
    }

    public void delete(Long id) {
        storage.delete(id);
    }

    public Review findById(Long id) {
        return storage.findById(id).orElseThrow(() -> new NotFoundException("Review not found"));
    }

    public List<Review> findAll(Long filmId, int count) {
        return storage.findAll(filmId, count);
    }

    public void like(Long reviewId, Long userId) {
        storage.like(reviewId, userId);
    }

    public void dislike(Long reviewId, Long userId) {
        storage.dislike(reviewId, userId);
    }

    public void removeReaction(Long reviewId, Long userId) {
        storage.removeLikeOrDislike(reviewId, userId);
    }
}
