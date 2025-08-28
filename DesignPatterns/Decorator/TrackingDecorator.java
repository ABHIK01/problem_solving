package DesignPatterns.Decorator;

/**
 * Concrete decorator that adds package tracking to orders.
 * Provides real-time tracking information and notifications.
 */
public class TrackingDecorator extends OrderServiceDecorator {
    private String trackingLevel;
    private double trackingFee;
    
    public TrackingDecorator(OrderService orderService, String trackingLevel) {
        super(orderService);
        this.trackingLevel = trackingLevel;
        
        // Set tracking fee based on level
        switch (trackingLevel.toLowerCase()) {
            case "basic":
                this.trackingFee = 2.99;
                break;
            case "detailed":
                this.trackingFee = 5.99;
                break;
            case "premium":
                this.trackingFee = 9.99;
                break;
            default:
                this.trackingFee = 2.99;
                this.trackingLevel = "basic";
        }
    }
    
    @Override
    public String processOrder(String orderId) {
        String result = super.processOrder(orderId);
        String trackingNumber = generateTrackingNumber();
        return result + "\n📍 " + capitalizeFirst(trackingLevel) + " tracking enabled for order " + 
               orderId + " (Tracking #: " + trackingNumber + ")";
    }
    
    @Override
    public double calculateTotalCost(double baseOrderCost) {
        double baseCost = super.calculateTotalCost(baseOrderCost);
        return baseCost + trackingFee;
    }
    
    @Override
    public String getServiceDescription() {
        return super.getServiceDescription() + " + " + capitalizeFirst(trackingLevel) + 
               " Tracking ($" + String.format("%.2f", trackingFee) + ")";
    }
    
    private String generateTrackingNumber() {
        return "TRK" + System.currentTimeMillis() % 1000000;
    }
    
    private String capitalizeFirst(String str) {
        if (str == null || str.isEmpty()) return str;
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }
    
    public String getTrackingLevel() {
        return trackingLevel;
    }
    
    public double getTrackingFee() {
        return trackingFee;
    }
}