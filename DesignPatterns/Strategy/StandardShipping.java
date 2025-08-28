package DesignPatterns.Strategy;

/**
 * Standard shipping strategy implementation.
 * Provides cost-effective shipping with longer delivery times.
 */
public class StandardShipping implements ShippingStrategy {
    private static final double BASE_RATE = 5.99;
    private static final double WEIGHT_RATE = 0.50; // per pound
    private static final double DISTANCE_RATE = 0.001; // per mile
    private static final double INTERNATIONAL_MULTIPLIER = 2.5;
    
    @Override
    public double calculateCost(double weight, double distance, boolean isInternational) {
        double cost = BASE_RATE + (weight * WEIGHT_RATE) + (distance * DISTANCE_RATE);
        
        if (isInternational) {
            cost *= INTERNATIONAL_MULTIPLIER;
        }
        
        return Math.round(cost * 100.0) / 100.0; // Round to 2 decimal places
    }
    
    @Override
    public int getEstimatedDeliveryDays(double distance, boolean isInternational) {
        if (isInternational) {
            return 10 + (int)(distance / 1000); // 10+ days for international
        } else {
            return 5 + (int)(distance / 500); // 5+ days for domestic
        }
    }
    
    @Override
    public String getMethodName() {
        return "Standard Shipping";
    }
}