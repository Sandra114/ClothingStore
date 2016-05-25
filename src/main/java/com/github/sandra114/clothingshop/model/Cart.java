package com.github.sandra114.clothingshop.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

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

    public void deleteItem(Size size) {
        sizeMap.remove(size);
    }

    public void updateItem(Size size, int count) {
        sizeMap.put(size, count);
    }

    public Stream<Map.Entry<Size, Integer>> getItems() {
        return sizeMap.entrySet().stream();
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
}
