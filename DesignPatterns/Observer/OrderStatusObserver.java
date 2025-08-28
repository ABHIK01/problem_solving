package DesignPatterns.Observer;

/**
 * Observer interface for order status notifications.
 * This interface defines the contract for objects that want to be notified
 * when an order status changes.
 */
public interface OrderStatusObserver {
    /**
     * Called when an order status changes.
     * 
     * @param orderId The ID of the order whose status changed
     * @param oldStatus The previous status of the order
     * @param newStatus The new status of the order
     * @param customerEmail The customer's email address
     */
    void onStatusChanged(String orderId, String oldStatus, String newStatus, String customerEmail);
}