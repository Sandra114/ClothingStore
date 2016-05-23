package com.github.sandra114.clothingshop.controller;

import com.github.sandra114.clothingshop.dao.CategoryDao;
import com.github.sandra114.clothingshop.dao.CategoryDaoImpl;
import com.github.sandra114.clothingshop.dao.ItemDescriptionDao;
import com.github.sandra114.clothingshop.dao.ItemDescriptionDaoImpl;
import com.github.sandra114.clothingshop.model.ItemDescription;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author Sandra
 */
@WebServlet("/items")
public class DescriptionController extends HttpServlet {
    //    private static final String EDIT_ACTION = "edit";
//    private static final String DELETE_ACTION = "delete";
//    private static final String ADD_ACTION = "add";
    private static final String SHOW_ACTION = "show";
    private static final String ACTION = "action";
    private static final String ITEMS = "/items.jsp";
    private static final String ITEM_DESCRIPTIONS = "itemDescriptions";
    private static final String GENDER = "gender";
    private static final String CATEGORY = "category";
    private static final String CATEGORIES = "categories";
    private static final String ID = "id";
    private static final String ITEM = "item";
    private static final String SEARCH = "search";
//    private static final String ITEM = "/item.jsp";

    private final ItemDescriptionDao dao = new ItemDescriptionDaoImpl();
    private final CategoryDao categoryDao = new CategoryDaoImpl();

    private Function<String, Predicate<ItemDescription>> genderFunc =
            s -> s == null ? i -> true : i -> i.getGender().trim().startsWith(s);
    private Function<String, Predicate<ItemDescription>> categoryFunc =
            s -> s == null ? i -> true : i -> i.getCategory().getId() == Integer.valueOf(s);

    private Function<String, Predicate<ItemDescription>> search =
            s -> s == null ? i -> true : i -> i.getTitle().toLowerCase().trim().contains(s);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter(ACTION);
        req.setAttribute(CATEGORIES, categoryDao.getAll());
        if (action != null) {
            switch (action) {
                case SHOW_ACTION:
                    String itemId = req.getParameter(ID);
                    ItemDescription item = dao.getById(Integer.valueOf(itemId));
                    req.setAttribute(ITEM, item);
            }
        } else {
            String gender = req.getParameter(GENDER);
            String categoryId = req.getParameter(CATEGORY);
            String se = req.getParameter(SEARCH).toLowerCase().trim();
            List<ItemDescription> allItems = dao.getAll().stream()
                    .filter(genderFunc.apply(gender))
                    .filter(categoryFunc.apply(categoryId))
                    .filter(search.apply(se))
                    .collect(Collectors.toList());
            req.setAttribute(ITEM_DESCRIPTIONS, allItems);
        }
        RequestDispatcher view = req.getRequestDispatcher(ITEMS);
        view.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
