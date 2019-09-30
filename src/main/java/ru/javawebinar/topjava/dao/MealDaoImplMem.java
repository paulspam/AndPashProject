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
        for (Meal meal: MealsUtil.MEALS
             ) {
            saveMeal(meal);
        }
    }

//    static {
//        Meal meal1 = new Meal(AUTO_ID.getAndIncrement(), LocalDateTime.of(2015, Month.MAY, 30, 8, 0), "Завтрак", 500);
//        mealsRep.put(meal1.getId(), meal1);
//        Meal meal2 = new Meal(AUTO_ID.getAndIncrement(), LocalDateTime.of(2015, Month.MAY, 30, 12, 0), "Обед", 900);
//        mealsRep.put(meal2.getId(), meal1);
//        Meal meal3 = new Meal(AUTO_ID.getAndIncrement(), LocalDateTime.of(2015, Month.MAY, 30, 19, 0), "Ужин", 700);
//        mealsRep.put(meal3.getId(), meal1);
//        Meal meal4 = new Meal(AUTO_ID.getAndIncrement(), LocalDateTime.of(2015, Month.MAY, 31, 9, 0), "Завтрак", 500);
//        mealsRep.put(meal4.getId(), meal1);
//        Meal meal5 = new Meal(AUTO_ID.getAndIncrement(), LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 1100);
//        mealsRep.put(meal5.getId(), meal1);
//        Meal meal6 = new Meal(AUTO_ID.getAndIncrement(), LocalDateTime.of(2015, Month.MAY, 31, 18, 0), "Ужин", 800);
//        mealsRep.put(meal6.getId(), meal1);
//
//
//    }
    // TODO Make Multythreading CRUD
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

