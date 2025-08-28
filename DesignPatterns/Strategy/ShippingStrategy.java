package DesignPatterns.Strategy;

/**
 * Strategy interface for shipping cost calculation.
 * Different shipping methods implement this interface to provide
 * their own cost calculation logic.
 */
public interface ShippingStrategy {
    /**
     * Calculate shipping cost based on weight, distance, and other factors.
     * 
     * @param weight Package weight in pounds
     * @param distance Shipping distance in miles
     * @param isInternational Whether the shipment is international
     * @return Shipping cost in USD
     */
    double calculateCost(double weight, double distance, boolean isInternational);
    
    /**
     * Get estimated delivery days for this shipping method.
     * 
     * @param distance Shipping distance in miles
     * @param isInternational Whether the shipment is international
     * @return Estimated delivery days
     */
    int getEstimatedDeliveryDays(double distance, boolean isInternational);
    
    /**
     * Get the name of this shipping method.
     * 
     * @return Shipping method name
     */
    String getMethodName();
}