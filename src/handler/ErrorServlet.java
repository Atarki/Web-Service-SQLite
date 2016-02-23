package handler;

import main.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ErrorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        String errorData = (String) req.getAttribute("errorData");

        Map<String, Object> dataErrorMap = new HashMap<>();

        dataErrorMap.put("info", errorData);
        dataErrorMap.put("users", "");

        resp.getWriter().println(PageGenerator.instance().getPage("main.html", dataErrorMap));
    }
}
