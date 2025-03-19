package edu.poly.asmjava5.dao;

import java.util.ArrayList;
import java.util.List;

import edu.poly.asmjava5.entity.ProductEntity;
import edu.poly.asmjava5.item.CartItem;

import java.util.HashMap;
import java.util.Map;

public class CartDAO {
    private Map<Long, CartItem> items = new HashMap<>();  //Map items là nơi lưu trữ dữ liệu giỏ hàng trong bộ nhớ của ứng dụng.

    public void addProduct(ProductEntity product) {
        if (items.containsKey(product.getId())) {
            items.get(product.getId()).increaseQuantity(); 
        } else {
            items.put(product.getId(), new CartItem(product)); 
        }
    }

    public List<CartItem> getProducts() {
        return new ArrayList<>(items.values()); 
    }

    public void clear() {
        items.clear();
    }

    public void removeProduct(Long productId) {
        items.remove(productId);
    }
    public String getTotalPrice() {
        double totalPrice = 0;
        for (CartItem item : items.values()) {
            totalPrice += Double.parseDouble(item.getTotalPrice());
        }
        return String.format("%.0f", totalPrice);
    }
}
