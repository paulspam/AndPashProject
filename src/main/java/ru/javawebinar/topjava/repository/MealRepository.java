package ru.javawebinar.topjava.repository;

import org.springframework.cglib.core.Local;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

public interface MealRepository {

    Meal save(Meal Meal, int userId);

    boolean delete(int id, int userId);

    Meal get(int id, int userId);

    List<Meal> getAll(int userId);

    List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId);

}
