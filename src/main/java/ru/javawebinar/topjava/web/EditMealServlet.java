package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditMealServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        log.info("getAll");
//        request.setAttribute("mealList", MealsUtil.getWithExceeded(MealsUtil.MEALS, MealsUtil.DEFAULT_CALORIES_PER_DAY));
        request.getRequestDispatcher("/editmeal.jsp").forward(request, response);

    }
}
