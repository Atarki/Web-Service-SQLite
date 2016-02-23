package handler;

import main.PageGenerator;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UserListServlet extends HttpServlet{
    private Map<String, Object> pageData = new HashMap<>();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        UserService userService = UserService.getUserService();

        pageData.put("users", userService.getAll());
        pageData.put("info", "User added successful");

        resp.getWriter().println(PageGenerator.instance().getPage("users.html", pageData));
    }
}
