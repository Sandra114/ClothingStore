package com.github.sandra114.clothingshop.controller;

import com.github.sandra114.clothingshop.dao.CategoryDao;
import com.github.sandra114.clothingshop.dao.CategoryDaoImpl;
import com.github.sandra114.clothingshop.model.Category;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Sandra
 */
@WebServlet("/category")
public class CategoryController extends HttpServlet {
    private static final String CATEGORIES = "categories";
    private static final String CATEGORIES_JSP = "categories.jsp";

    private final CategoryDao dao = new CategoryDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Category> categories = dao.getAll();
        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");
        req.setAttribute(CATEGORIES, categories);
        RequestDispatcher view = req.getRequestDispatcher(CATEGORIES_JSP);
        view.forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
