package handler;

import main.PageGenerator;
import service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AddUserServlet extends HttpServlet {
    private Map<String, Object> pageData = new HashMap<>();
    private UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        pageData.put("users", "");
        pageData.put("info", "");
        resp.getWriter().println(PageGenerator.instance().getPage("main.html", pageData));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            String name = req.getParameter("name");
            int age = Integer.parseInt(req.getParameter("age"));
            String dateOfBirth = req.getParameter("dateOfBirth");

            userService = UserService.getUserService();
            userService.save(id, name, age, dateOfBirth);

            resp.sendRedirect("/users");

        } catch (NumberFormatException | NullPointerException e) {
            e.printStackTrace();

            req.setAttribute("errorData", "Please fill the form correctly");
            RequestDispatcher rd = req.getRequestDispatcher("/error");
            rd.include(req, resp);
        }


    }
}
