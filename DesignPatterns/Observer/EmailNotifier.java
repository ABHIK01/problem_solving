package DesignPatterns.Observer;

/**
 * Concrete observer for email notifications.
 * Sends email notifications when order status changes.
 */
public class EmailNotifier implements OrderStatusObserver {
    private String serviceName;
    
    public EmailNotifier(String serviceName) {
        this.serviceName = serviceName;
    }
    
    @Override
    public void onStatusChanged(String orderId, String oldStatus, String newStatus, String customerEmail) {
        System.out.println("📧 EMAIL NOTIFICATION (" + serviceName + "):");
        System.out.println("   To: " + customerEmail);
        System.out.println("   Subject: Order " + orderId + " Status Update");
        System.out.println("   Body: Your order status has changed from '" + oldStatus + 
                          "' to '" + newStatus + "'");
        
        // Simulate email sending delay
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        System.out.println("   ✅ Email sent successfully!");
        System.out.println();
    }
}