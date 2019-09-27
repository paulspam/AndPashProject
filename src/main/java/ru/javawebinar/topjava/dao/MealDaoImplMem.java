package ru.javawebinar.topjava.dao;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static org.slf4j.LoggerFactory.getLogger;

public class MealDaoImplMem implements MealDao {
    private static final Logger log = getLogger(MealDaoImplMem.class);
    private static final AtomicInteger AUTO_ID = new AtomicInteger(0);
    private static Map<Integer, Meal> meals = new HashMap<>();

    static {
        Meal meal1 = new Meal(AUTO_ID.getAndIncrement(), LocalDateTime.of(2015, Month.MAY, 30, 8, 0), "Завтрак", 500);
        meals.put(meal1.getId(), meal1);
        Meal meal2 = new Meal(AUTO_ID.getAndIncrement(), LocalDateTime.of(2015, Month.MAY, 30, 12, 0), "Обед", 900);
        meals.put(meal2.getId(), meal1);
        Meal meal3 = new Meal(AUTO_ID.getAndIncrement(), LocalDateTime.of(2015, Month.MAY, 30, 19, 0), "Ужин", 700);
        meals.put(meal3.getId(), meal1);
        Meal meal4 = new Meal(AUTO_ID.getAndIncrement(), LocalDateTime.of(2015, Month.MAY, 31, 9, 0), "Завтрак", 500);
        meals.put(meal4.getId(), meal1);
        Meal meal5 = new Meal(AUTO_ID.getAndIncrement(), LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 1100);
        meals.put(meal5.getId(), meal1);
        Meal meal6 = new Meal(AUTO_ID.getAndIncrement(), LocalDateTime.of(2015, Month.MAY, 31, 18, 0), "Ужин", 800);
        meals.put(meal6.getId(), meal1);


    }
    // TODO Make Multythreading CRUD
    @Override
    public void addMeal(Meal meal) {
        meal.setId(AUTO_ID.getAndIncrement());
        meals.put(meal.getId(), meal);
    }

    @Override
    public void updateMeal(Meal meal) {
        if (meal.getId() != null) {
            meals.put(meal.getId(), meal);
        }
    }

    @Override
    public void removeMeal(Integer id) {
        meals.remove(id);
    }

   @Override
    public Meal getMealById(Integer id) {
        return meals.get(id);
    }

    @Override
    public List<Meal> listMeals() {
        return new ArrayList<>(meals.values());
    }
}

