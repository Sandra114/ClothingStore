package com.github.sandra114.clothingshop.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Sandra
 */
public class Cart {
    private Map<Size, Integer> sizeMap = new HashMap<>();

    public Cart() {
    }

    public void addItem(Size size) {
        sizeMap.compute(size, (size1, integer) -> integer == null ? 1 : integer + 1);
    }

    public void deleteItem(int id) {
        Size s = getById(id);
        sizeMap.remove(s);
    }

    public void plus(int id) {
        Size s = getById(id);
        sizeMap.computeIfPresent(s, (size, integer) -> integer + 1);
    }

    public void minus(int id) {
        Size s = getById(id);
        sizeMap.computeIfPresent(s, (size, integer) -> integer - 1);
    }

    public void clear() {
        sizeMap = new HashMap<>();
    }

    public Set<Map.Entry<Size, Integer>> getItems() {
        return sizeMap.entrySet();
    }

    public int getSize() {
        return sizeMap.entrySet().stream()
                .map(Map.Entry::getValue)
                .reduce((c1, c2) -> c1 + c2)
                .orElse(0);
    }

    public Double getTotalPrice() {
        return sizeMap.entrySet().stream()
                .map(entry -> entry.getKey().getItems().getPrice() * entry.getValue())
                .reduce((p1, p2) -> p1 + p2).orElse(0.0);
    }

    private Size getById(int id) {
        return sizeMap.keySet().stream().filter(size -> size.getId() == id).findFirst().orElse(null);
    }
}
