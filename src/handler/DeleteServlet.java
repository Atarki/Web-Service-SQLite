package handler;

import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteServlet extends HttpServlet{
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getHeader("id");

        UserService userService = UserService.getUserService();
        userService.delete(id);

        System.out.println("User has been deleted with id: " + id);
        resp.addHeader("info", "User has been deleted with id: " + id);
//        resp.sendRedirect("/users");
    }
}
