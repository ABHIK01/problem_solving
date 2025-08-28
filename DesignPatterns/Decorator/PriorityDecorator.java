package DesignPatterns.Decorator;

/**
 * Concrete decorator that adds priority handling to orders.
 * Ensures faster processing and handling throughout the fulfillment process.
 */
public class PriorityDecorator extends OrderServiceDecorator {
    private String priorityLevel;
    private double priorityFee;
    
    public PriorityDecorator(OrderService orderService, String priorityLevel) {
        super(orderService);
        this.priorityLevel = priorityLevel;
        
        // Set priority fee based on level
        switch (priorityLevel.toLowerCase()) {
            case "high":
                this.priorityFee = 12.99;
                break;
            case "urgent":
                this.priorityFee = 24.99;
                break;
            case "critical":
                this.priorityFee = 49.99;
                break;
            default:
                this.priorityFee = 12.99;
                this.priorityLevel = "high";
        }
    }
    
    @Override
    public String processOrder(String orderId) {
        String result = super.processOrder(orderId);
        return result + "\n⚡ " + capitalizeFirst(priorityLevel) + " priority processing applied to order " + 
               orderId + " (Queue position: Front)";
    }
    
    @Override
    public double calculateTotalCost(double baseOrderCost) {
        double baseCost = super.calculateTotalCost(baseOrderCost);
        return baseCost + priorityFee;
    }
    
    @Override
    public String getServiceDescription() {
        return super.getServiceDescription() + " + " + capitalizeFirst(priorityLevel) + 
               " Priority Processing ($" + String.format("%.2f", priorityFee) + ")";
    }
    
    private String capitalizeFirst(String str) {
        if (str == null || str.isEmpty()) return str;
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }
    
    public String getPriorityLevel() {
        return priorityLevel;
    }
    
    public double getPriorityFee() {
        return priorityFee;
    }
}