package DesignPatterns.Strategy;

/**
 * Overnight shipping strategy implementation.
 * Fastest delivery option with premium pricing.
 */
public class OvernightShipping implements ShippingStrategy {
    private static final double BASE_RATE = 29.99;
    private static final double WEIGHT_RATE = 2.50; // per pound
    private static final double DISTANCE_RATE = 0.005; // per mile
    private static final double INTERNATIONAL_MULTIPLIER = 4.0;
    private static final double MAX_DOMESTIC_DISTANCE = 2000; // miles
    
    @Override
    public double calculateCost(double weight, double distance, boolean isInternational) {
        // Overnight shipping not available for very long distances internationally
        if (isInternational && distance > 5000) {
            throw new IllegalArgumentException("Overnight shipping not available for distances over 5000 miles internationally");
        }
        
        // Overnight shipping not available for very long domestic distances
        if (!isInternational && distance > MAX_DOMESTIC_DISTANCE) {
            throw new IllegalArgumentException("Overnight shipping not available for distances over " + MAX_DOMESTIC_DISTANCE + " miles domestically");
        }
        
        double cost = BASE_RATE + (weight * WEIGHT_RATE) + (distance * DISTANCE_RATE);
        
        if (isInternational) {
            cost *= INTERNATIONAL_MULTIPLIER;
        }
        
        return Math.round(cost * 100.0) / 100.0;
    }
    
    @Override
    public int getEstimatedDeliveryDays(double distance, boolean isInternational) {
        if (isInternational) {
            return 2; // Next business day for international
        } else {
            return 1; // Next business day for domestic
        }
    }
    
    @Override
    public String getMethodName() {
        return "Overnight Shipping";
    }
}