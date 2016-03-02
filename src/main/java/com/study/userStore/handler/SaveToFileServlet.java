package com.study.userStore.handler;


import com.study.userStore.dao.UserRepository;
import com.study.userStore.main.PageGenerator;
import com.study.userStore.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


public class SaveToFileServlet extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(SaveToFileServlet.class.getName());
    private Map<String, Object> pageData = new HashMap<>();
    private UserService userService;


    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        userService.saveToFile();

        pageData.put("info", "File was saved successful.");
        pageData.put("users", UserRepository.getInstance().getAllUsers());

        if (LOG.isLoggable(Level.INFO)) {
            LOG.info("File was saved successful.");
        }

        resp.getWriter().println(PageGenerator.instance().getPage("users.html", pageData));
    }
}
