package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.dao.MealDaoImplMem;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.service.MealServiceImplMem;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalTime;
import java.util.List;

public class MealServlet extends HttpServlet {
    private MealService mealService;

    @Override
    public void init() throws ServletException {
        super.init();
        mealService = new MealServiceImplMem();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        log.info("getAll");
//        request.setAttribute("mealList", MealsUtil.getWithExceeded(MealsUtil.MEALS, MealsUtil.DEFAULT_CALORIES_PER_DAY));
        String action = request.getParameter("action");

        switch (action == null ? "all" : action) {
            case "edit":
                request.setAttribute("meal", mealService.getMealById(Integer.parseInt(request.getParameter("id"))));
                request.getRequestDispatcher("/meal.jsp").forward(request, response);
                break;
            case "delete":
                mealService.removeMeal(Integer.parseInt(request.getParameter("id")));
                response.sendRedirect("meals");
                break;
            case "all":
            default:
                request.setAttribute("mealList", MealsUtil.getWithExceeded(mealService.listMeals(), MealsUtil.DEFAULT_CALORIES_PER_DAY));
                request.getRequestDispatcher("/meals.jsp").forward(request, response);

        }



    }
}
