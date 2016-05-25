package com.github.sandra114.clothingshop.controller;

import com.github.sandra114.clothingshop.dao.CategoryDao;
import com.github.sandra114.clothingshop.dao.CategoryDaoImpl;
import com.github.sandra114.clothingshop.model.Cart;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Sandra
 */
@WebServlet("/cart")
public class CartController extends HttpServlet {
    private CategoryDao dao = new CategoryDaoImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cartAction = req.getParameter("cartAction");
        int sizeId = Integer.valueOf(cartAction.substring(1, cartAction.length()));
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cartAction.startsWith("m")) {
            cart.minus(sizeId);
        } else if (cartAction.startsWith("p")) {
            cart.plus(sizeId);
        } else {
            cart.deleteItem(sizeId);
        }
        forward(req, resp);
    }

    private void forward(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("categories", dao.getAll());
        RequestDispatcher view = req.getRequestDispatcher("cart.jsp");
        view.forward(req, resp);
    }
}
