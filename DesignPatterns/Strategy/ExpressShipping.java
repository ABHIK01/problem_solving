package DesignPatterns.Strategy;

/**
 * Express shipping strategy implementation.
 * Faster delivery at higher cost.
 */
public class ExpressShipping implements ShippingStrategy {
    private static final double BASE_RATE = 15.99;
    private static final double WEIGHT_RATE = 1.25; // per pound
    private static final double DISTANCE_RATE = 0.003; // per mile
    private static final double INTERNATIONAL_MULTIPLIER = 3.0;
    
    @Override
    public double calculateCost(double weight, double distance, boolean isInternational) {
        double cost = BASE_RATE + (weight * WEIGHT_RATE) + (distance * DISTANCE_RATE);
        
        if (isInternational) {
            cost *= INTERNATIONAL_MULTIPLIER;
        }
        
        return Math.round(cost * 100.0) / 100.0;
    }
    
    @Override
    public int getEstimatedDeliveryDays(double distance, boolean isInternational) {
        if (isInternational) {
            return 3 + (int)(distance / 2000); // 3+ days for international
        } else {
            return 2 + (int)(distance / 1000); // 2+ days for domestic
        }
    }
    
    @Override
    public String getMethodName() {
        return "Express Shipping";
    }
}