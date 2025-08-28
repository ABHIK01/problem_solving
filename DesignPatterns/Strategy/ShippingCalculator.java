package DesignPatterns.Strategy;

/**
 * Context class for shipping cost calculation.
 * Uses the Strategy pattern to delegate shipping cost calculation
 * to different shipping strategy implementations.
 */
public class ShippingCalculator {
    private ShippingStrategy strategy;
    
    public ShippingCalculator(ShippingStrategy strategy) {
        this.strategy = strategy;
    }
    
    /**
     * Set a new shipping strategy.
     */
    public void setStrategy(ShippingStrategy strategy) {
        this.strategy = strategy;
    }
    
    /**
     * Calculate shipping cost using the current strategy.
     */
    public double calculateShippingCost(double weight, double distance, boolean isInternational) {
        if (strategy == null) {
            throw new IllegalStateException("No shipping strategy set");
        }
        return strategy.calculateCost(weight, distance, isInternational);
    }
    
    /**
     * Get estimated delivery days using the current strategy.
     */
    public int getEstimatedDeliveryDays(double distance, boolean isInternational) {
        if (strategy == null) {
            throw new IllegalStateException("No shipping strategy set");
        }
        return strategy.getEstimatedDeliveryDays(distance, isInternational);
    }
    
    /**
     * Get the current shipping method name.
     */
    public String getCurrentMethodName() {
        if (strategy == null) {
            return "No strategy set";
        }
        return strategy.getMethodName();
    }
    
    /**
     * Generate a comprehensive shipping quote.
     */
    public ShippingQuote generateQuote(double weight, double distance, boolean isInternational) {
        double cost = calculateShippingCost(weight, distance, isInternational);
        int deliveryDays = getEstimatedDeliveryDays(distance, isInternational);
        String methodName = getCurrentMethodName();
        
        return new ShippingQuote(methodName, cost, deliveryDays, weight, distance, isInternational);
    }
    
    /**
     * Inner class representing a shipping quote.
     */
    public static class ShippingQuote {
        private String methodName;
        private double cost;
        private int deliveryDays;
        private double weight;
        private double distance;
        private boolean isInternational;
        
        public ShippingQuote(String methodName, double cost, int deliveryDays, 
                           double weight, double distance, boolean isInternational) {
            this.methodName = methodName;
            this.cost = cost;
            this.deliveryDays = deliveryDays;
            this.weight = weight;
            this.distance = distance;
            this.isInternational = isInternational;
        }
        
        @Override
        public String toString() {
            return String.format("📦 %s Quote:\n" +
                               "   💰 Cost: $%.2f\n" +
                               "   📅 Delivery: %d business days\n" +
                               "   ⚖️  Weight: %.1f lbs\n" +
                               "   📍 Distance: %.0f miles\n" +
                               "   🌍 International: %s",
                               methodName, cost, deliveryDays, weight, distance, 
                               isInternational ? "Yes" : "No");
        }
        
        // Getters
        public String getMethodName() { return methodName; }
        public double getCost() { return cost; }
        public int getDeliveryDays() { return deliveryDays; }
        public double getWeight() { return weight; }
        public double getDistance() { return distance; }
        public boolean isInternational() { return isInternational; }
    }
}