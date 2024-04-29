package org.vv.secondTask.entities;

import java.util.Comparator;
import java.util.List;

public class InventoryManagement {
    public static final String NAME = "name";
    public static final String STOCK = "stock";
    public static final String PRICE = "price";
    private final List<Product> products;

    public InventoryManagement(List<Product> products) {
        this.products = products;
    }

    public List<Product> sortProducts(String sortKey, boolean ascending) {
        Comparator<Product> comparator = getComparator(sortKey);

        if (!ascending) {
            comparator = comparator.reversed();
        }

        products.sort(comparator);
        return products;
    }

    private Comparator<Product> getComparator(String sortKey) {
        switch (sortKey) {
            case NAME:
                return Comparator.comparing(Product::getName);
            case PRICE:
                return Comparator.comparingDouble(Product::getPrice);
            case STOCK:
                return Comparator.comparingInt(Product::getStock);
            default:
                throw new IllegalArgumentException("Invalid sort key: " + sortKey);
        }
    }
}
