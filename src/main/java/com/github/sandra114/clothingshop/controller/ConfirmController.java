package com.github.sandra114.clothingshop.controller;

import com.github.sandra114.clothingshop.dao.*;
import com.github.sandra114.clothingshop.model.Client;
import com.github.sandra114.clothingshop.model.Order;
import com.github.sandra114.clothingshop.model.OrderItem;
import com.github.sandra114.clothingshop.model.Size;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * @author Sandra
 */
@WebServlet("/confirm")
public class ConfirmController extends HttpServlet {
    private static final String ITEMS = "items";
    private static final String SEND_STATUS = "Отправлен";
    public static final String MESSAGE = "message";
    private final ClientDao dao = new ClientDaoImpl();
    private final SizeDao sizeDao = new SizeDaoImpl();
    private final OrderDao orderDao = new OrderDaoImpl();
    private final OrderItemDao orderItemDao = new OrderItemDaoImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String phone = req.getParameter("phone");
        String address = req.getParameter("address");
        int sizeId = Integer.valueOf(req.getParameter("sizeId").trim());

        Client client = new Client(firstName, lastName, phone, address);
        dao.add(client);
        Size size = sizeDao.getById(sizeId);
        Order order = new Order(new Date(System.currentTimeMillis()), SEND_STATUS, client);
        OrderItem orderItem = new OrderItem(1, order, size);
        orderDao.add(order);
        orderItemDao.add(orderItem);
        req.getSession().setAttribute(MESSAGE, Boolean.TRUE);
        resp.sendRedirect(ITEMS);
    }
}
