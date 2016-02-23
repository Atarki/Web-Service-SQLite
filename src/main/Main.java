package main;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import handler.DeleteServlet;
import handler.ErrorServlet;
import handler.AddUserServlet;
import handler.SaveToFileServlet;
import handler.UserListServlet;

public class Main {
    public static void main(String[] args) throws Exception {
        AddUserServlet addUserServlet = new AddUserServlet();
        ErrorServlet errorServlet = new ErrorServlet();
        SaveToFileServlet saveToFileServlet = new SaveToFileServlet();
        DeleteServlet deleteServlet = new DeleteServlet();
        UserListServlet userListServlet = new UserListServlet();

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(addUserServlet), "/main");
        context.addServlet(new ServletHolder(errorServlet), "/error");
        context.addServlet(new ServletHolder(saveToFileServlet), "/save");
        context.addServlet(new ServletHolder(deleteServlet), "/delete");
        context.addServlet(new ServletHolder(userListServlet), "/users");

        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setResourceBase("src/public");

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{resourceHandler, context});

        Server server = new Server(8080);
        server.setHandler(handlers);
        server.start();
    }
}