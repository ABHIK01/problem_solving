package DesignPatterns.Decorator;

/**
 * Basic implementation of OrderService.
 * This is the core component that provides basic order processing functionality.
 */
public class BasicOrderService implements OrderService {
    
    @Override
    public String processOrder(String orderId) {
        return "Basic order processing completed for " + orderId;
    }
    
    @Override
    public double calculateTotalCost(double baseOrderCost) {
        return baseOrderCost;
    }
    
    @Override
    public String getServiceDescription() {
        return "Basic Order Processing";
    }
}