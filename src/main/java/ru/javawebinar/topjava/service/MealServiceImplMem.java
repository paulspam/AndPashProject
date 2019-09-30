package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.dao.MealDao;
import ru.javawebinar.topjava.dao.MealDaoImplMem;
import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public class MealServiceImplMem implements MealService {

    private MealDao mealDao = new MealDaoImplMem();

    @Override
    public void saveMeal(Meal meal) {
        mealDao.saveMeal(meal);
    }

    @Override
    public void removeMeal(Integer id) {
        mealDao.removeMeal(id);
    }

    @Override
    public Meal getMealById(Integer id) {
        return mealDao.getMealById(id);
    }

    @Override
    public List<Meal> listMeals() {
        return mealDao.listMeals();
    }
}
