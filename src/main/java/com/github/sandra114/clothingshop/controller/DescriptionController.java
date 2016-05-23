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
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author Sandra
 */
@WebServlet("/items")
public class DescriptionController extends HttpServlet {
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
    private static final String SORT = "sort";

    private final ItemDescriptionDao dao = new ItemDescriptionDaoImpl();
    private final CategoryDao categoryDao = new CategoryDaoImpl();

    private Function<String, Predicate<ItemDescription>> genderFunc =
            s -> !ifExists(s) || s.equals("null") ? i -> true : i -> i.getGender().trim().startsWith(s);
    private Function<String, Predicate<ItemDescription>> categoryFunc =
            s -> !ifExists(s) ? i -> true : i -> i.getCategory().getId() == Integer.valueOf(s);

    private Function<String, Predicate<ItemDescription>> search =
            s -> !ifExists(s) ? i -> true : i ->
                    i.getTitle().toLowerCase().trim().contains(s.toLowerCase().trim())
                            || i.getCategory().getTitle().toLowerCase().trim().contains(s.toLowerCase().trim());

    private static boolean ifExists(String s) {
        return s != null && !s.equals("null");
    }

    private Comparator<ItemDescription> priceHighLow = (i1, i2) -> Double.compare(i2.getPrice(), i1.getPrice());
    private Comparator<ItemDescription> priceLowHigh = (i1, i2) -> Double.compare(i1.getPrice(), i2.getPrice());

    private Comparator<ItemDescription> azOrder = (o1, o2) -> o1.getTitle().compareTo(o2.getTitle());
    private Comparator<ItemDescription> zaOrder = (o1, o2) -> o2.getTitle().compareTo(o1.getTitle());

    private Function<String, Comparator<ItemDescription>> sort = s -> {
        if (s == null) return (o1, o2) -> 0;
        switch (s) {
            case "highLow":
                return priceHighLow;
            case "lowHigh":
                return priceLowHigh;
            case "az":
                return azOrder;
            case "za":
                return zaOrder;
            default:
                return (o1, o2) -> 0;
        }
    };

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
            String se = req.getParameter(SEARCH);
            String sortReq = req.getParameter(SORT);
            List<ItemDescription> allItems = dao.getAll().stream()
                    .filter(genderFunc.apply(gender))
                    .filter(categoryFunc.apply(categoryId))
                    .filter(search.apply(se))
                    .sorted(sort.apply(sortReq))
                    .collect(Collectors.toList());
            req.setAttribute(ITEM_DESCRIPTIONS, allItems);
        }
        RequestDispatcher view = req.getRequestDispatcher(ITEMS);
        view.forward(req, resp);
    }

}
