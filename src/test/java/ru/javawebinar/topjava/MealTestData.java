package ru.javawebinar.topjava;

import ru.javawebinar.topjava.matcher.BeanMatcher;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
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

//
//            (100000, '2015-05-30 10:00:00', 'Завтрак', 500),
//            (100000, '2015-05-30 13:00:00', 'Обед', 1000),
//            (100000, '2015-05-30 20:00:00', 'Ужин', 500),
//            (100000, '2015-05-31 10:00:00', 'Завтрак', 500),
//            (100000, '2015-05-31 13:00:00', 'Обед', 1000),
//            (100000, '2015-05-31 20:00:00', 'Ужин', 510),
//            (100001, '2015-06-01 14:00:00', 'Админ ланч', 510),
//            (100001, '2015-06-01 21:00:00', 'Админ ужин', 1500);
//
/*    public static final BeanMatcher<Meal> MATCHER = new BeanMatcher<>(
            new Function<Meal, String>(){
                @Override
                public String apply(Meal meal){
                    return meal.toString();
                }
            }
);*/
    public static final BeanMatcher<Meal> MATCHER = new BeanMatcher<>();

}
