package com.github.sandra114.clothingshop.controller;

import com.github.sandra114.clothingshop.dao.*;
import com.github.sandra114.clothingshop.model.*;

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
    private static final String MESSAGE = "message";
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

        Client client = new Client(firstName, lastName, phone, address);
        dao.add(client);
        Order order = new Order(new Date(System.currentTimeMillis()), SEND_STATUS, client);
        orderDao.add(order);

        if (req.getParameter("sizeId") != null) {
            int sizeId = Integer.valueOf(req.getParameter("sizeId").trim());
            Size size = sizeDao.getById(sizeId);
            OrderItem orderItem = new OrderItem(1, order, size);
            orderItemDao.add(orderItem);
        } else {
            Cart cart = (Cart) req.getSession().getAttribute("cart");
            cart.getItems().stream()
                    .filter(entry -> entry.getValue() != 0)
                    .forEach(entry -> orderItemDao.add(new OrderItem(entry.getValue(), order, entry.getKey())));
            cart.clear();
        }
        req.getSession().setAttribute(MESSAGE, Boolean.TRUE);
        resp.sendRedirect(ITEMS);
    }
}
