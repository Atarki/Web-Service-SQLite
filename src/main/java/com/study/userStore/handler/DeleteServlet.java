package com.study.userStore.handler;


import com.study.userStore.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DeleteServlet extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(DeleteServlet.class.getName());
    private UserService userService;

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getHeader("id");
        String name = req.getHeader("name");
        userService.deleteUser(id, name);

        if (LOG.isLoggable(Level.INFO)) {
            LOG.info("User has been deleted with id: " + id + " name: " + name);
        }
        resp.addHeader("info", "User has been deleted with id: " + id + " name: " + name);
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
