package DesignPatterns.Builder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Builder Pattern Implementation
 * OrderBuilder provides a fluent interface for constructing complex Order objects
 */
public class OrderBuilder {
    
    // Required fields
    String orderId;
    String customerId;
    List<Order.OrderItem> items;
    double totalAmount;
    
    // Optional fields with default values
    String shippingAddress;
    String billingAddress;
    String discountCode;
    boolean giftWrap = false;
    String giftMessage;
    String shippingMethod = "Standard";
    LocalDateTime deliveryDate;
    boolean expressShipping = false;
    String customerNotes;
    
    public OrderBuilder() {
        this.orderId = "ORD-" + UUID.randomUUID().toString().substring(0, 8);
        this.items = new ArrayList<>();
        this.totalAmount = 0.0;
    }
    
    // Required field setters
    public OrderBuilder customerId(String customerId) {
        this.customerId = customerId;
        return this;
    }
    
    public OrderBuilder addItem(String productId, String productName, int quantity, double unitPrice) {
        Order.OrderItem item = new Order.OrderItem(productId, productName, quantity, unitPrice);
        this.items.add(item);
        this.totalAmount += item.getTotalPrice();
        return this;
    }
    
    public OrderBuilder addItem(Order.OrderItem item) {
        this.items.add(item);
        this.totalAmount += item.getTotalPrice();
        return this;
    }
    
    // Optional field setters (fluent interface)
    public OrderBuilder shippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
        return this;
    }
    
    public OrderBuilder billingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
        return this;
    }
    
    public OrderBuilder discountCode(String discountCode) {
        this.discountCode = discountCode;
        // Apply discount logic here
        if (discountCode != null && !discountCode.isEmpty()) {
            this.totalAmount *= 0.9; // 10% discount for demo
        }
        return this;
    }
    
    public OrderBuilder giftWrap(boolean giftWrap) {
        this.giftWrap = giftWrap;
        if (giftWrap) {
            this.totalAmount += 5.99; // Gift wrap fee
        }
        return this;
    }
    
    public OrderBuilder giftMessage(String giftMessage) {
        this.giftMessage = giftMessage;
        return this;
    }
    
    public OrderBuilder shippingMethod(String shippingMethod) {
        this.shippingMethod = shippingMethod;
        return this;
    }
    
    public OrderBuilder deliveryDate(LocalDateTime deliveryDate) {
        this.deliveryDate = deliveryDate;
        return this;
    }
    
    public OrderBuilder expressShipping(boolean expressShipping) {
        this.expressShipping = expressShipping;
        if (expressShipping) {
            this.totalAmount += 15.99; // Express shipping fee
        }
        return this;
    }
    
    public OrderBuilder customerNotes(String customerNotes) {
        this.customerNotes = customerNotes;
        return this;
    }
    
    // Validation and build method
    public Order build() {
        validateOrder();
        return new Order(this);
    }
    
    private void validateOrder() {
        if (customerId == null || customerId.trim().isEmpty()) {
            throw new IllegalStateException("Customer ID is required");
        }
        if (items.isEmpty()) {
            throw new IllegalStateException("At least one item is required");
        }
        if (totalAmount <= 0) {
            throw new IllegalStateException("Total amount must be positive");
        }
        // Set billing address to shipping address if not provided
        if (billingAddress == null && shippingAddress != null) {
            billingAddress = shippingAddress;
        }
    }
    
    // Helper methods for common configurations
    public OrderBuilder basicOrder(String customerId, String productId, String productName, int quantity, double unitPrice) {
        return this.customerId(customerId)
                  .addItem(productId, productName, quantity, unitPrice);
    }
    
    public OrderBuilder giftOrder(String customerId, String shippingAddress, String giftMessage) {
        return this.customerId(customerId)
                  .shippingAddress(shippingAddress)
                  .giftWrap(true)
                  .giftMessage(giftMessage);
    }
    
    public OrderBuilder rushOrder(String customerId, LocalDateTime deliveryDate) {
        return this.customerId(customerId)
                  .deliveryDate(deliveryDate)
                  .expressShipping(true)
                  .shippingMethod("Express");
    }
}