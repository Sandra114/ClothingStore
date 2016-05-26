package com.github.sandra114.clothingshop.auth;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author Sandra
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private Map<String, String> admins = new HashMap<>();

    @Override
    public void init() throws ServletException {
        admins.put("admin", "admin");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String pass = req.getParameter("pass");

        boolean isLogin = Optional.ofNullable(admins.get(login))
                .flatMap(s -> Optional.of(s.equals(pass)))
                .orElse(false);
        if (isLogin) {
            req.getSession().setAttribute("user", login);
            resp.sendRedirect("items");
        } else {
            req.getSession().setAttribute("message", true);
            resp.sendRedirect("auth.jsp");
        }
    }
}
