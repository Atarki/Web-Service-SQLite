package main.java.com.study.userStore.handler;

import main.java.com.study.userStore.dao.User;
import main.java.com.study.userStore.main.PageGenerator;
import main.java.com.study.userStore.service.UserService;

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
        resp.getWriter().println(PageGenerator.instance().getPage("main.main.resources.html", pageData));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            User user = new User();
            user.setId(Integer.parseInt(req.getParameter("id")));
            user.setAge(Integer.parseInt(req.getParameter("age")));
            user.setName(req.getParameter("name"));
            user.setDateOfBirth(req.getParameter("dateOfBirth"));

            userService.addToRepository(user);

            resp.sendRedirect("/users");
        } catch (Exception e) {
            System.out.println("Exception Error: " + e.getMessage());

            req.setAttribute("errorData", "Please fill the form correctly. " + e.getMessage());
            RequestDispatcher rd = req.getRequestDispatcher("/error");
            rd.include(req, resp);
        }
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
