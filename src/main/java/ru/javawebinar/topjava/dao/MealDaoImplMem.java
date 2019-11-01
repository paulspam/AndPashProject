package ru.javawebinar.topjava.dao;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import static org.slf4j.LoggerFactory.getLogger;

public class MealDaoImplMem implements MealDao {
    private static final Logger log = getLogger(MealDaoImplMem.class);
    private AtomicInteger AUTO_ID = new AtomicInteger(0);
    private Map<Integer, Meal> mealsRep = new ConcurrentHashMap<>();

    {
        for (Meal meal : MealsUtil.MEALS
        ) {
            saveMeal(meal);
        }
    }

    @Override
    public void saveMeal(Meal meal) {
        if (meal.isNew()) {
            meal.setId(AUTO_ID.getAndIncrement());
        }
        mealsRep.put(meal.getId(), meal);
    }

    @Override
    public void removeMeal(Integer id) {
        mealsRep.remove(id);
    }

    @Override
    public Meal getMealById(Integer id) {
        return mealsRep.get(id);
    }

    @Override
    public List<Meal> listMeals() {
        return new ArrayList<>(mealsRep.values());
    }
}

