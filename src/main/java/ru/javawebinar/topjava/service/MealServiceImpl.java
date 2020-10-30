package ru.javawebinar.topjava.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;


@Service
@RequiredArgsConstructor
public class MealServiceImpl implements MealService {

    private final MealRepository repository;

//    public MealServiceImpl(MealRepository repository) {
//        this.repository = repository;
//    }

    @Override
    public Meal create(Meal meal, Integer userId) {
        return repository.save(meal, userId);
    }

    @Override
    public Meal update(Meal meal, Integer userId) {
        return checkNotFoundWithId(repository.save(meal, userId), meal.getId());
    }

    @Override
    public void delete(int id, Integer userId) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id, userId), id);
    }

    @Override
    public Meal get(int id, Integer userId) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id, userId), id);
    }

    @Override
    public List<Meal> getAll(Integer userId) {
        return repository.getAll(userId);
    }

    @Override
    public List<Meal> getBetweenDateTimes(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        return repository.getBetween(startDateTime, endDateTime, userId);
    }
}