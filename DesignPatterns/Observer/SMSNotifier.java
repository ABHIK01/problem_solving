package DesignPatterns.Observer;

/**
 * Concrete observer for SMS notifications.
 * Sends SMS notifications when order status changes.
 */
public class SMSNotifier implements OrderStatusObserver {
    private String provider;
    
    public SMSNotifier(String provider) {
        this.provider = provider;
    }
    
    @Override
    public void onStatusChanged(String orderId, String oldStatus, String newStatus, String customerEmail) {
        System.out.println("📱 SMS NOTIFICATION (" + provider + "):");
        System.out.println("   To: +1-XXX-XXX-XXXX (registered with " + customerEmail + ")");
        System.out.println("   Message: Order " + orderId + " is now " + newStatus.toUpperCase());
        
        // Simulate SMS sending delay
        try {
            Thread.sleep(80);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        System.out.println("   ✅ SMS sent successfully!");
        System.out.println();
    }
}