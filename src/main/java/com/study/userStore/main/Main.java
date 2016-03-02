package com.study.userStore.main;

import com.study.userStore.dao.DataSource;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import com.study.userStore.handler.DeleteServlet;
import com.study.userStore.handler.ErrorServlet;
import com.study.userStore.handler.AddUserServlet;
import com.study.userStore.handler.SaveToFileServlet;
import com.study.userStore.handler.UserListServlet;
import com.study.userStore.dao.UserDao;
import com.study.userStore.service.UserService;

public class Main {
    public static void main(String[] args) throws Exception {
        AddUserServlet addUserServlet = new AddUserServlet();
        ErrorServlet errorServlet = new ErrorServlet();
        SaveToFileServlet saveToFileServlet = new SaveToFileServlet();
        DeleteServlet deleteServlet = new DeleteServlet();
        UserListServlet userListServlet = new UserListServlet();

        UserService userService = new UserService();
        DataSource dataSource = new DataSource();
        UserDao userDao = UserDao.getUserDao();

        userListServlet.setUserService(userService);
        addUserServlet.setUserService(userService);
        deleteServlet.setUserService(userService);
        saveToFileServlet.setUserService(userService);

        userService.setUserDao(userDao);
        userDao.setDataSource(dataSource);
        userService.initialize();

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(addUserServlet), "/main");
        context.addServlet(new ServletHolder(errorServlet), "/error");
        context.addServlet(new ServletHolder(saveToFileServlet), "/save");
        context.addServlet(new ServletHolder(deleteServlet), "/delete");
        context.addServlet(new ServletHolder(userListServlet), "/users");

        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setResourceBase("src/main/resources");

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{resourceHandler, context});

        Server server = new Server(8080);
        server.setHandler(handlers);
        server.start();
    }
}
