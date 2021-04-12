package ru.javawebinar.topjava;

import ru.javawebinar.topjava.matcher.BeanMatcher;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

import static ru.javawebinar.topjava.model.BaseEntity.START_SEQ;

public class MealTestData {

    public static int MEAL_ID = START_SEQ + 2;

    public static final List<Meal> MEALS = Arrays.asList(
            new Meal(MEAL_ID++, LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
            new Meal(MEAL_ID++, LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
            new Meal(MEAL_ID++, LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
            new Meal(MEAL_ID++, LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 500),
            new Meal(MEAL_ID++, LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 1000),
            new Meal(MEAL_ID++, LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
    );

public static final BeanMatcher<Meal> MATCHER = new BeanMatcher<>(
        (expected, actual) -> expected == actual ||
                (Objects.equals(expected.getId(), actual.getId())
                        && Objects.equals(expected.getDateTime(), actual.getDateTime())
                        && Objects.equals(expected.getDescription(), actual.getDescription())
                        && Objects.equals(expected.getCalories(), actual.getCalories())
                )
);
}
