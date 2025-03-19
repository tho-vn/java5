package edu.poly.asmjava5.item;

import edu.poly.asmjava5.entity.ProductEntity;

public class CartItem {
    private ProductEntity product;
    private int quantity;

    public CartItem(ProductEntity product) {
        this.product = product;
        this.quantity = 1;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity; 
    }

    public void increaseQuantity() {
        this.quantity++;
    }

    public String getTotalPrice() {
        long totalPrice = Math.round(product.getPrice() * quantity); 
        return String.valueOf(totalPrice); 
    }
    public void decreaseQuantity() {
        if (quantity > 1) {
            quantity--;
        }
    }   
}