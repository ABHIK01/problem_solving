package DesignPatterns.Observer;

/**
 * Concrete observer for push notifications.
 * Sends push notifications to mobile apps when order status changes.
 */
public class PushNotifier implements OrderStatusObserver {
    private String appName;
    
    public PushNotifier(String appName) {
        this.appName = appName;
    }
    
    @Override
    public void onStatusChanged(String orderId, String oldStatus, String newStatus, String customerEmail) {
        System.out.println("🔔 PUSH NOTIFICATION (" + appName + " App):");
        System.out.println("   User: " + customerEmail);
        System.out.println("   Title: Order Update");
        System.out.println("   Message: Your order " + orderId + " is " + newStatus);
        System.out.println("   Badge: New");
        
        // Simulate push notification delay
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        System.out.println("   ✅ Push notification sent successfully!");
        System.out.println();
    }
}