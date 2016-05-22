package com.github.sandra114.clothingshop.controller;

import com.github.sandra114.clothingshop.dao.CategoryDao;
import com.github.sandra114.clothingshop.dao.CategoryDaoImpl;
import com.github.sandra114.clothingshop.dao.SizeDao;
import com.github.sandra114.clothingshop.dao.SizeDaoImpl;
import com.github.sandra114.clothingshop.model.Size;

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
@WebServlet("/buy")
public class BuyController extends HttpServlet {
    private static final String ITEM_SIZE_PARAM = "itemSize";
    private static final String BUY_TYPE_PARAM = "buyType";
    private static final String ONE_CLICK = "oneClick";

    private static final String REQ_SIZE = "reqSize";
    private static final String CATEGORIES = "categories";

    private static final String ONE_CLICK_PAGE = "one_click.jsp";

    private final SizeDao sizeDao = new SizeDaoImpl();
    private final CategoryDao categoryDao = new CategoryDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int itemId = Integer.valueOf(req.getParameter(ITEM_SIZE_PARAM));
        String buyType = req.getParameter(BUY_TYPE_PARAM);

        if (buyType.startsWith(ONE_CLICK)) {
            Size size = sizeDao.getById(itemId);
            req.setAttribute(CATEGORIES, categoryDao.getAll());
            req.setAttribute(REQ_SIZE, size);
            RequestDispatcher view = req.getRequestDispatcher(ONE_CLICK_PAGE);
            view.forward(req, resp);
        }
    }
}
