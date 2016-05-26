package com.github.sandra114.clothingshop.controller;

import com.github.sandra114.clothingshop.dao.OrderDao;
import com.github.sandra114.clothingshop.dao.OrderDaoImpl;
import com.github.sandra114.clothingshop.model.Order;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Sandra
 */
@WebServlet("/admin/orders")
public class OrderController extends HttpServlet {
    private final OrderDao orderDao = new OrderDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Order> orders = orderDao.getAll();
        forward(req, resp, orders);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int orderId = Integer.valueOf(req.getParameter("orderId").trim());
        String newStatus = req.getParameter("orderAction").equals("cancel") ? "Отменен" : "Исполнен";
        List<Order> orders = orderDao.getAll();
        Order order = orders.stream().filter(o -> o.getId() == orderId).findFirst().get();
        order.setStatus(newStatus);
        orderDao.update(order);
        forward(req, resp, orders);

    }

    private void forward(HttpServletRequest req, HttpServletResponse resp, List<Order> orders) throws ServletException, IOException {
        req.setAttribute("orders", orders.stream().sorted((o1, o2) -> Integer.compare(o2.getId(), o1.getId())).collect(Collectors.toList()));
        RequestDispatcher dispatcher = req.getRequestDispatcher("/orders.jsp");
        dispatcher.forward(req, resp);
    }
}
