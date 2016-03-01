package handler;

import dao.UserRepository;
import main.PageGenerator;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class SaveToFileServlet extends HttpServlet {
    private Map<String, Object> pageData = new HashMap<>();
    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        userService.saveToFile();

        pageData.put("info", "File was saved successful.");
        pageData.put("users", UserRepository.getUserRepository().getAllUsers());

        resp.getWriter().println(PageGenerator.instance().getPage("users.html", pageData));
    }
}
