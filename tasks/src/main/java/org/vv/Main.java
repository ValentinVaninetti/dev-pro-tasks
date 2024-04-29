package org.vv;

import org.vv.secondTask.entities.InventoryManagement;
import org.vv.secondTask.entities.Product;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args){
        List<Product> products = new ArrayList<>();
        products.add(new Product("Product A", 100, 5));
        products.add(new Product("Product B", 200, 3));
        products.add(new Product("Product C", 50, 10));

        printSortedProducts("name", false, products);
        printSortedProducts("stock", true, products);
        printSortedProducts("price", true, products);
        printSortedProducts("price", false, products);
    }
    public static void printSortedProducts(String sortKey, Boolean ascending,List<Product> products){
        InventoryManagement inventoryManager = new InventoryManagement(products);
        List<Product> sortedProducts = inventoryManager.sortProducts(sortKey, ascending);

        for (Product product : sortedProducts) {
            System.out.println(product);
        }
    }

}
