package DesignPatterns.Decorator;

/**
 * Abstract base decorator for order services.
 * This class implements the OrderService interface and maintains a reference
 * to another OrderService object, allowing decorators to be chained.
 */
public abstract class OrderServiceDecorator implements OrderService {
    protected OrderService orderService;
    
    public OrderServiceDecorator(OrderService orderService) {
        this.orderService = orderService;
    }
    
    @Override
    public String processOrder(String orderId) {
        return orderService.processOrder(orderId);
    }
    
    @Override
    public double calculateTotalCost(double baseOrderCost) {
        return orderService.calculateTotalCost(baseOrderCost);
    }
    
    @Override
    public String getServiceDescription() {
        return orderService.getServiceDescription();
    }
}