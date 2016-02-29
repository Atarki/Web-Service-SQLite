package handler;

import dao.User;
import dao.UserDao;
import main.PageGenerator;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserListServlet extends HttpServlet{
    private Map<String, Object> pageData = new HashMap<>();
    private UserService userService;
    private UserDao userDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<User> users = userService.getAll();
        pageData.put("users", users);
        pageData.put("info", "User added successful");

        resp.getWriter().println(PageGenerator.instance().getPage("users.html", pageData));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        userService.save();
        userService.initialize();
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
