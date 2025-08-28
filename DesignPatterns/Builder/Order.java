package DesignPatterns.Builder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

/**
 * Order class with complex construction requirements
 * Perfect candidate for Builder pattern
 */
public class Order {
    
    // Required fields
    private final String orderId;
    private final String customerId;
    private final List<OrderItem> items;
    private final double totalAmount;
    
    // Optional fields
    private final String customerEmail;
    private final String shippingAddress;
    private final String billingAddress;
    private final String discountCode;
    private final boolean giftWrap;
    private final String giftMessage;
    private final String shippingMethod;
    private final LocalDateTime deliveryDate;
    private final boolean expressShipping;
    private final String customerNotes;
    
    // Package-private constructor - accessible to Builder in same package
    Order(OrderBuilder builder) {
        this.orderId = builder.orderId;
        this.customerId = builder.customerId;
        this.items = new ArrayList<>(builder.items);
        this.totalAmount = builder.totalAmount;
        this.customerEmail = builder.customerEmail;
        this.shippingAddress = builder.shippingAddress;
        this.billingAddress = builder.billingAddress;
        this.discountCode = builder.discountCode;
        this.giftWrap = builder.giftWrap;
        this.giftMessage = builder.giftMessage;
        this.shippingMethod = builder.shippingMethod;
        this.deliveryDate = builder.deliveryDate;
        this.expressShipping = builder.expressShipping;
        this.customerNotes = builder.customerNotes;
    }
    
    // Getters
    public String getOrderId() { return orderId; }
    public String getCustomerId() { return customerId; }
    public List<OrderItem> getItems() { return new ArrayList<>(items); }
    public double getTotalAmount() { return totalAmount; }
    public String getCustomerEmail() { return customerEmail; }
    public String getShippingAddress() { return shippingAddress; }
    public String getBillingAddress() { return billingAddress; }
    public String getDiscountCode() { return discountCode; }
    public boolean isGiftWrap() { return giftWrap; }
    public String getGiftMessage() { return giftMessage; }
    public String getShippingMethod() { return shippingMethod; }
    public LocalDateTime getDeliveryDate() { return deliveryDate; }
    public boolean isExpressShipping() { return expressShipping; }
    public String getCustomerNotes() { return customerNotes; }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Order{")
          .append("orderId='").append(orderId).append('\'')
          .append(", customerId='").append(customerId).append('\'')
          .append(", items=").append(items.size()).append(" items")
          .append(", totalAmount=").append(totalAmount)
          .append(", shippingAddress='").append(shippingAddress).append('\'')
          .append(", billingAddress='").append(billingAddress).append('\'')
          .append(", discountCode='").append(discountCode).append('\'')
          .append(", giftWrap=").append(giftWrap)
          .append(", giftMessage='").append(giftMessage).append('\'')
          .append(", shippingMethod='").append(shippingMethod).append('\'')
          .append(", deliveryDate=").append(deliveryDate)
          .append(", expressShipping=").append(expressShipping)
          .append(", customerNotes='").append(customerNotes).append('\'')
          .append('}');
        return sb.toString();
    }
    
    /**
     * Static method to get a new builder instance
     */
    public static OrderBuilder builder() {
        return new OrderBuilder();
    }
    
    /**
     * Inner class representing an order item
     */
    public static class OrderItem {
        private final String productId;
        private final String productName;
        private final int quantity;
        private final double unitPrice;
        
        public OrderItem(String productId, String productName, int quantity, double unitPrice) {
            this.productId = productId;
            this.productName = productName;
            this.quantity = quantity;
            this.unitPrice = unitPrice;
        }
        
        public String getProductId() { return productId; }
        public String getProductName() { return productName; }
        public int getQuantity() { return quantity; }
        public double getUnitPrice() { return unitPrice; }
        public double getTotalPrice() { return quantity * unitPrice; }
        
        @Override
        public String toString() {
            return "OrderItem{" +
                   "productId='" + productId + '\'' +
                   ", productName='" + productName + '\'' +
                   ", quantity=" + quantity +
                   ", unitPrice=" + unitPrice +
                   ", totalPrice=" + getTotalPrice() +
                   '}';
        }
    }
}