package ru.javawebinar.topjava.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.util.NumberUtils;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.util.DbPopulator;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.model.BaseEntity.START_SEQ;
import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ZERO;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
public class MealServiceTest {

    static {
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService service;

    @Autowired
    private DbPopulator dbPopulator;

    @Before
    public void setUp() throws Exception {
        dbPopulator.execute();
    }

    @Test
    public void get() {
        for (Meal meal: MEALS) {
            Meal testmeal = service.get(meal.getId(), AuthorizedUser.id());
            MATCHER.assertEquals(meal, testmeal);
        }
    }

    @Test (expected = NotFoundException.class)
    public void getNotFound() {
        for (Meal meal: MEALS) {
            Meal testmeal = service.get(meal.getId(), INTEGER_ZERO);
            MATCHER.assertEquals(meal, testmeal);
        }
    }

    @Test
    public void delete() {
        service.delete(START_SEQ + 2, AuthorizedUser.id());
        List<Meal> newMeals = MEALS.stream()
                .skip(1)
                .sorted(Comparator.comparing(Meal::getDateTime).reversed())
                .collect(Collectors.toList());
        MATCHER.assertCollectionEquals(newMeals, service.getAll(AuthorizedUser.id()));
    }

    @Test (expected = NotFoundException.class)
    public void deleteNotFound() {
        service.delete(START_SEQ + 2, INTEGER_ZERO);
    }

    @Test
    public void getBetweenDates() {
        LocalDate startTestDate = LocalDate.of(2015, Month.MAY, 30);
        LocalDate endTestDate = LocalDate.of(2015, Month.MAY, 30);
        List<Meal> newMeals = MEALS.stream()
                .filter(m -> (m.getDate().isAfter(startTestDate) && m.getDate().isBefore(endTestDate))
                || (m.getDate().isEqual(startTestDate) || m.getDate().isEqual(endTestDate)))
                .sorted(Comparator.comparing(Meal::getDateTime).reversed())
                .collect(Collectors.toList());
        MATCHER.assertCollectionEquals(newMeals, service.getBetweenDates(startTestDate, endTestDate, AuthorizedUser.id()));
    }

    @Test
    public void getBetweenDateTimes() {
        LocalDateTime startTestDate = LocalDateTime.of(2015, Month.MAY, 30, 10, 00);
        LocalDateTime endTestDate = LocalDateTime.of(2015, Month.MAY, 31, 10, 00);
        List<Meal> newMeals = MEALS.stream()
                .filter(m -> (m.getDateTime().isAfter(startTestDate) && m.getDateTime().isBefore(endTestDate))
                        || (m.getDateTime().isEqual(startTestDate) || m.getDateTime().isEqual(endTestDate)))
                .sorted(Comparator.comparing(Meal::getDateTime).reversed())
                .collect(Collectors.toList());
        MATCHER.assertCollectionEquals(newMeals, service.getBetweenDateTimes(startTestDate, endTestDate, AuthorizedUser.id()));
    }

    @Test
    public void getAll() {
        List<Meal> newMeals = MEALS.stream()
                .sorted(Comparator.comparing(Meal::getDateTime).reversed())
                .collect(Collectors.toList());
        MATCHER.assertCollectionEquals(newMeals, service.getAll(AuthorizedUser.id()));
    }

    @Test
    public void update() {
        List<Meal> newMeals = MEALS.stream()
                .sorted(Comparator.comparing(Meal::getDateTime).reversed())
                .collect(Collectors.toList());
        Integer mealId = START_SEQ + 2;
        Meal newMeal = new Meal(mealId, LocalDateTime.of(2015, Month.MAY, 30, 11, 15), "Новый ужин 2", 1500);
        for (Meal meal: newMeals) {
            if (meal.getId().equals(mealId)) {
                meal.setDateTime(newMeal.getDateTime());
                meal.setDescription(newMeal.getDescription());
                meal.setCalories(newMeal.getCalories());
            }
        }
        service.update(newMeal, AuthorizedUser.id());
        MATCHER.assertCollectionEquals(newMeals, service.getAll(AuthorizedUser.id()));
    }

    @Test (expected = NotFoundException.class)
    public void updateNotFound() {
        Integer mealId = START_SEQ + 2;
        Meal newMeal = new Meal(mealId, LocalDateTime.of(2015, Month.MAY, 30, 11, 15), "Новый ужин 2", 1500);
        service.update(newMeal, INTEGER_ZERO);
    }

    @Test
    public void save() {
        List<Meal> newMeals = MEALS.stream()
                .sorted(Comparator.comparing(Meal::getDateTime).reversed())
                .collect(Collectors.toList());
        Integer newMealId = START_SEQ + newMeals.size() + 4; //New counter for newMeals
        Meal newMeal = new Meal(newMealId, LocalDateTime.of(2015, Month.MAY, 31, 15, 15), "Новый обед", 2500);
        newMeals.add(newMeal);
        service.save(new Meal(LocalDateTime.of(2015, Month.MAY, 31, 15, 15), "Новый обед", 2500), AuthorizedUser.id());
//        service.save(newMeal, AuthorizedUser.id());
        MATCHER.assertCollectionEquals(newMeals.stream()
                        .sorted(Comparator.comparing(Meal::getDateTime).reversed())
                        .collect(Collectors.toList()),
                service.getAll(AuthorizedUser.id()));
    }

    @Test (expected = NotFoundException.class)
    public void saveNotFound() {
        Integer newMealId = START_SEQ + MEALS.size() + 4; //New counter for newMeals
        Meal newMeal = new Meal(newMealId, LocalDateTime.of(2015, Month.MAY, 30, 11, 15), "Новый ужин 2", 1500);
        service.update(newMeal, INTEGER_ZERO);
    }

}