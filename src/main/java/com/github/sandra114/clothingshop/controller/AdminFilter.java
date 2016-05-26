package com.github.sandra114.clothingshop.controller;

import com.github.sandra114.clothingshop.dao.CategoryDao;
import com.github.sandra114.clothingshop.dao.CategoryDaoImpl;
import com.sun.deploy.net.HttpRequest;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Sandra
 */
@WebFilter("/admin")
public class AdminFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("AdminFilter init");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain)
            throws IOException, ServletException {
        System.out.println("AdminFilter do");

        HttpSession session = ((HttpServletRequest) req).getSession();

        Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
        if (isAdmin != null && isAdmin) {
            CategoryDao dao = new CategoryDaoImpl();
            req.setAttribute("categories", dao.getAll());
            filterChain.doFilter(req, resp);
            return;
        }

        ((HttpServletResponse) resp).sendRedirect("auth.jsp");
    }

    @Override
    public void destroy() {
        System.out.println("AdminFilter destroy");
    }
}
