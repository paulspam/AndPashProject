package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.dao.MealDaoImplMem;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.service.MealServiceImplMem;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditMealServlet extends HttpServlet {
    private MealService mealService = new MealServiceImplMem();


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        log.info("getAll");
        Integer id = Integer.parseInt(request.getParameter("id"));
        Meal meal = mealService.getMealById(id);
        request.setAttribute("meal", mealService.getMealById(Integer.parseInt(request.getParameter("id"))));
        request.getRequestDispatcher("/meal.jsp").forward(request, response);

    }
}
