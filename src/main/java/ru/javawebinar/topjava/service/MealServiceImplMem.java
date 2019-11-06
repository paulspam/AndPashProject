package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.dao.MealDao;
import ru.javawebinar.topjava.dao.MealDaoImplMem;
import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public class MealServiceImplMem implements MealService {

    private MealDao mealDao = new MealDaoImplMem();

    @Override
    public void save(Meal meal) {
        mealDao.save(meal);
    }

    @Override
    public void remove(Integer id) {
        mealDao.remove(id);
    }

    @Override
    public Meal getById(Integer id) {
        return mealDao.getById(id);
    }

    @Override
    public List<Meal> getAll() {
        return mealDao.getAll();
    }
}
