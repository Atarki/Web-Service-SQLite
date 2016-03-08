package com.study.userStore.handler;

import com.study.userStore.dao.User;
import com.study.userStore.handler.util.PageGenerator;
import com.study.userStore.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class AddUserServlet extends HttpServlet {
    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-dd-MM");
    private static final Logger LOG = Logger.getLogger(DeleteServlet.class.getName());
    private Map<String, Object> pageData = new HashMap<>();
    private UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        pageData.put("users", "");
        pageData.put("info", "");
        resp.getWriter().println(PageGenerator.instance().getPage("starter.html", pageData));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            User user = new User();
            user.setName(req.getParameter("name"));
            user.setDateOfBirth(LocalDate.parse(req.getParameter("dateOfBirth"),dateTimeFormatter));
            userService.addToRepository(user);

            resp.sendRedirect("/users");
        } catch (Exception e) {
            LOG.warning("Exception Error: " + e.getMessage());

            req.setAttribute("errorData", "Please fill the form correctly. " + e.getMessage());
            RequestDispatcher rd = req.getRequestDispatcher("/error");
            rd.include(req, resp);
        }
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
