package handler;

import dao.UserDao;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteServlet extends HttpServlet{
    private UserService userService;

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getHeader("id");

        UserDao userDao = UserDao.getUserDao();
        userDao.deleteFromNewList(id);
//        userService.deleteUser(id);

        System.out.println("User has been deleted with id: " + id);
        resp.addHeader("info", "User has been deleted with id: " + id);
//        resp.sendRedirect("/users");
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
