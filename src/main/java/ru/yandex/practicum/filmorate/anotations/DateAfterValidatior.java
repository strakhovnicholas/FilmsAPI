package ru.yandex.practicum.filmorate.anotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class DateAfterValidatior implements ConstraintValidator<DateAfter, LocalDate> {
    private LocalDate expectedValue;

    @Override
    public void initialize(DateAfter constraintAnnotation) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        ConstraintValidator.super.initialize(constraintAnnotation);
        this.expectedValue = LocalDate.parse(constraintAnnotation.datestring(), formatter);
    }

    @Override
    public boolean isValid(LocalDate localDate, ConstraintValidatorContext constraintValidatorContext) {
        if (Objects.isNull(localDate)) {
            return false;
        }

        return localDate.equals(expectedValue) || localDate.isAfter(expectedValue);
    }
}