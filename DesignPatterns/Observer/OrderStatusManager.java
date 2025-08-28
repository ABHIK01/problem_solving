package DesignPatterns.Observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Subject class that manages order status and notifies observers.
 * This class implements the Observer pattern by maintaining a list of observers
 * and notifying them when order status changes.
 */
public class OrderStatusManager {
    private List<OrderStatusObserver> observers;
    private String orderId;
    private String currentStatus;
    private String customerEmail;
    
    public OrderStatusManager(String orderId, String customerEmail) {
        this.observers = new ArrayList<>();
        this.orderId = orderId;
        this.currentStatus = "PENDING";
        this.customerEmail = customerEmail;
    }
    
    /**
     * Add an observer to be notified of status changes.
     */
    public void addObserver(OrderStatusObserver observer) {
        observers.add(observer);
        System.out.println("🔗 Observer added: " + observer.getClass().getSimpleName());
    }
    
    /**
     * Remove an observer from notifications.
     */
    public void removeObserver(OrderStatusObserver observer) {
        observers.remove(observer);
        System.out.println("❌ Observer removed: " + observer.getClass().getSimpleName());
    }
    
    /**
     * Update the order status and notify all observers.
     */
    public void updateStatus(String newStatus) {
        String oldStatus = this.currentStatus;
        this.currentStatus = newStatus;
        
        System.out.println("📋 Order Status Change: " + orderId + " (" + oldStatus + " → " + newStatus + ")");
        System.out.println("🔄 Notifying " + observers.size() + " observers...");
        System.out.println();
        
        // Notify all observers
        for (OrderStatusObserver observer : observers) {
            observer.onStatusChanged(orderId, oldStatus, newStatus, customerEmail);
        }
    }
    
    /**
     * Get current order status.
     */
    public String getCurrentStatus() {
        return currentStatus;
    }
    
    /**
     * Get order ID.
     */
    public String getOrderId() {
        return orderId;
    }
}