package DesignPatterns.Strategy;

/**
 * International shipping strategy implementation.
 * Specialized for international deliveries with customs handling.
 */
public class InternationalShipping implements ShippingStrategy {
    private static final double BASE_RATE = 35.99;
    private static final double WEIGHT_RATE = 3.00; // per pound
    private static final double DISTANCE_RATE = 0.002; // per mile
    private static final double CUSTOMS_FEE = 15.00;
    private static final double DUTY_RATE = 0.08; // 8% of package value
    
    private double packageValue;
    
    public InternationalShipping(double packageValue) {
        this.packageValue = packageValue;
    }
    
    @Override
    public double calculateCost(double weight, double distance, boolean isInternational) {
        if (!isInternational) {
            throw new IllegalArgumentException("International shipping strategy can only be used for international shipments");
        }
        
        double baseCost = BASE_RATE + (weight * WEIGHT_RATE) + (distance * DISTANCE_RATE);
        double customsFee = CUSTOMS_FEE;
        double dutyFee = packageValue * DUTY_RATE;
        
        double totalCost = baseCost + customsFee + dutyFee;
        
        return Math.round(totalCost * 100.0) / 100.0;
    }
    
    @Override
    public int getEstimatedDeliveryDays(double distance, boolean isInternational) {
        // International shipping with customs processing
        int baseDays = 7; // Base processing time
        int distanceDays = (int)(distance / 1500); // Additional days based on distance
        return baseDays + distanceDays;
    }
    
    @Override
    public String getMethodName() {
        return "International Shipping";
    }
    
    public double getCustomsFee() {
        return CUSTOMS_FEE;
    }
    
    public double getDutyFee() {
        return packageValue * DUTY_RATE;
    }
}