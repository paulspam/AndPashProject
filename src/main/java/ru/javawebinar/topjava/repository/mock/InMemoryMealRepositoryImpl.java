package ru.javawebinar.topjava.repository.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ZERO;
import static ru.javawebinar.topjava.repository.mock.InMemoryUserRepositoryImpl.ADMIN_ID;
import static ru.javawebinar.topjava.repository.mock.InMemoryUserRepositoryImpl.USER_ID;


@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryMealRepositoryImpl.class);
    private Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(INTEGER_ZERO);

    {
        MealsUtil.MEALS.forEach(m -> save(m, USER_ID));
        save(new Meal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Админ ужин", 600), ADMIN_ID);
        save(new Meal(LocalDateTime.of(2015, Month.JUNE, 1, 14, 0), "Админ обед", 500), ADMIN_ID);
    }

    @Override
    public Meal save(Meal meal, int userId) {
        Integer mealId = meal.getId();
        meal.setUserId(userId);
        if (meal.isNew()) {
            mealId = counter.incrementAndGet();
            meal.setId(mealId);
            repository.put(meal.getId(), meal);
            log.info("save meal {} for user {}", meal, userId);
        } else
            if (get(mealId, userId) == null) {
                return null;
            }

        return repository.computeIfPresent(mealId,
                (id, oldMeal) -> oldMeal.getUserId().equals(userId) ? meal : oldMeal);
    }

    @Override
    public boolean delete(int id, int userId) {
        log.info("delete meal {} for user {}", id, userId);
        return repository.remove(id, get(id, userId));
    }

    @Override
    public Meal get(int id, int userId) {
        log.info("get meal {} for user {}", id, userId);
        Meal getResult = repository.getOrDefault(id, new Meal(null, null, null, 0));
        return getResult.getUserId().equals(userId) ? getResult : null;
    }

    @Override
    public List<Meal> getAll(int userId) {
        log.info("getAll for user {}", userId);
        return repository.values().stream()
                .filter(meal -> meal.getUserId().equals(userId))
                .sorted(Comparator.comparing(Meal::getDateTime).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return getAll(userId).stream()
                .filter(meal -> DateTimeUtil.isBetween(meal.getDateTime(), startDate, endDate))
                .sorted(Comparator.comparing(Meal::getDateTime).reversed())
                .collect(Collectors.toList());
    }
}