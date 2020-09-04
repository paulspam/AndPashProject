package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.service.MealServiceImpl;
import ru.javawebinar.topjava.to.MealWithExceed;

import java.util.List;

@Controller
public class MealRestController {
    private static final Logger log = LoggerFactory.getLogger(MealRestController.class);

    @Autowired
    private MealServiceImpl service;

    public Meal get(int id) {
        int userId = AuthorizedUser.id();
        log.info("get meal {} for user {}", id, userId);
        return service.get(id, userId);
    }

    public Meal create(Meal meal) {
        int userId = AuthorizedUser.id();
        log.info("create meal {} for user {}", meal, userId);
        return service.create(meal, userId);
    }

    public Meal update(Meal meal) {
        int userId = AuthorizedUser.id();
        log.info("update meal {} for user {}", meal, userId);
        return service.update(meal, userId);
    }

    public void delete(int id) {
        int userId = AuthorizedUser.id();
        log.info("delete id {} for user {}", id, userId);
        service.delete(id, userId);
    }

    public List<Meal> getAll() {
        int userId = AuthorizedUser.id();
        log.info("getAll for user {}", userId);
        return service.getAll(userId);
    }

    public List<MealWithExceed> getAllWithExceed() {
        int userId = AuthorizedUser.id();
        int caloriesPerDay = AuthorizedUser.getCaloriesPerDay();
        log.info("getAllWithExceed for user {}", userId);
        return service.getAll(userId, caloriesPerDay);
    }





}