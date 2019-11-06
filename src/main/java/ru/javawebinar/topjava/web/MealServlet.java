package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.service.MealServiceImplMem;
import ru.javawebinar.topjava.util.MealsUtil;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import org.slf4j.Logger;
import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {

    private static final Logger LOG = getLogger(MealServlet.class);

    private MealService mealService;

    @Override
    public void init() throws ServletException {
        super.init();
        mealService = new MealServiceImplMem();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String sId = request.getParameter("id");
        Meal meal = new Meal(sId.isEmpty() ? null : Integer.valueOf(sId),
                LocalDateTime.parse(request.getParameter("dateTime")),
                request.getParameter("description"),
                Integer.valueOf(request.getParameter("calories")));
        mealService.save(meal);
        LOG.debug("meal " + meal.toString() + " saved");
        response.sendRedirect("meals");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null)
            action = "all";
        switch (action) {
            case "add":
            case "edit":
                Meal meal = action.equals("add") ?
                        new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000) :
                        mealService.getById(Integer.valueOf(request.getParameter("id")));
                if (action.equals("add")) {
                    LOG.debug("meal " + meal.toString() + " added");
                }
                if (action.equals("edit")) {
                    LOG.debug("meal " + meal.toString() + " edited");
                }
                request.setAttribute("meal", meal);
                request.getRequestDispatcher("/meal.jsp").forward(request, response);
                break;
            case "delete":
                LOG.debug("meal deleted");
                mealService.remove(Integer.parseInt(request.getParameter("id")));
                response.sendRedirect("meals");
                break;
            case "all":
            default:
                LOG.debug("forward to meals");
                request.setAttribute("mealList", MealsUtil.getWithExceeded(mealService.getAll(), MealsUtil.DEFAULT_CALORIES_PER_DAY));
                request.getRequestDispatcher("/meals.jsp").forward(request, response);
        }
    }
}
