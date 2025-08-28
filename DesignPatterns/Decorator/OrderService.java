package DesignPatterns.Decorator;

/**
 * Base component interface for order services.
 * This defines the core operations that can be enhanced through decoration.
 */
public interface OrderService {
    /**
     * Process the order with all applied enhancements.
     * 
     * @param orderId The order ID to process
     * @return Processing result description
     */
    String processOrder(String orderId);
    
    /**
     * Calculate total cost including all enhancements.
     * 
     * @param baseOrderCost The base cost of the order
     * @return Total cost with enhancements
     */
    double calculateTotalCost(double baseOrderCost);
    
    /**
     * Get description of all applied services.
     * 
     * @return Description of services
     */
    String getServiceDescription();
}