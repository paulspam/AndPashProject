package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface MealService {
    public void save(Meal meal);

    public void remove(Integer id);

    public Meal getById(Integer id);

    public List<Meal> getAll();
}
