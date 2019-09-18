package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface MealDao {
    public void addMeal(Meal meal);

    public void updateMeal(Meal meal);

    public void removeMeal (Meal meal);

    public Meal getMealById(Integer id);

    public List<Meal> listMeals();

}
