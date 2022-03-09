package com.epam.engx.cleancode.functions.task4;

import com.epam.engx.cleancode.functions.task4.thirdpartyjar.Product;

import java.util.Iterator;
import java.util.List;

public class Order {

    private List<Product> products;
    private double orderPrice = 0.0;

    public Double getPriceOfAvailableProducts() {
        getAvailableProducts(products.iterator());
        return getOrderPrice(orderPrice);
    }

    private void getAvailableProducts(Iterator<Product> iterator) {
        while (iterator.hasNext()) {
            Product product = iterator.next();
            if (!product.isAvailable())
                iterator.remove();
        }
    }

    private double getOrderPrice(double orderPrice) {
        for (Product product : products)
            orderPrice += product.getProductPrice();
        return orderPrice;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
