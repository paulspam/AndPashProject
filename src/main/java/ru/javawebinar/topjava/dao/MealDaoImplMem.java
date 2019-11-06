package ru.javawebinar.topjava.dao;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import static org.slf4j.LoggerFactory.getLogger;

public class MealDaoImplMem implements MealDao {
    private static final Logger LOG = getLogger(MealDaoImplMem.class);
    private AtomicInteger autoId = new AtomicInteger(0);
    private Map<Integer, Meal> mealsRep = new ConcurrentHashMap<>();

    {
        for (Meal meal : MealsUtil.MEALS){
            save(meal);
        }
    }

    @Override
    public void save(Meal meal) {
        if (meal.isNew()) {
            meal.setId(autoId.getAndIncrement());
        }
        mealsRep.put(meal.getId(), meal);
    }

    @Override
    public void remove(Integer id) {
        mealsRep.remove(id);
    }

    @Override
    public Meal getById(Integer id) {
        return mealsRep.get(id);
    }

    @Override
    public List<Meal> getAll() {
        return new ArrayList<>(mealsRep.values());
    }
}

