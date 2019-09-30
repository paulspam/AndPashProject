package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface MealService {
    public void saveMeal(Meal meal);

    public void removeMeal(Integer id);

    public Meal getMealById(Integer id);

    public List<Meal> listMeals();
}
