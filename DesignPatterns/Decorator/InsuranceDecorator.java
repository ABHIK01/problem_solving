package DesignPatterns.Decorator;

/**
 * Concrete decorator that adds insurance coverage to orders.
 * Provides protection against loss or damage during shipping.
 */
public class InsuranceDecorator extends OrderServiceDecorator {
    private double coverageAmount;
    private double insuranceRate;
    
    public InsuranceDecorator(OrderService orderService, double coverageAmount) {
        super(orderService);
        this.coverageAmount = coverageAmount;
        this.insuranceRate = 0.015; // 1.5% of coverage amount
    }
    
    @Override
    public String processOrder(String orderId) {
        String result = super.processOrder(orderId);
        return result + "\n🛡️  Insurance coverage activated for order " + orderId + 
               " (Coverage: $" + String.format("%.2f", coverageAmount) + ")";
    }
    
    @Override
    public double calculateTotalCost(double baseOrderCost) {
        double baseCost = super.calculateTotalCost(baseOrderCost);
        double insuranceCost = coverageAmount * insuranceRate;
        return baseCost + insuranceCost;
    }
    
    @Override
    public String getServiceDescription() {
        return super.getServiceDescription() + " + Insurance Coverage ($" + 
               String.format("%.2f", coverageAmount * insuranceRate) + ")";
    }
    
    public double getCoverageAmount() {
        return coverageAmount;
    }
    
    public double getInsuranceCost() {
        return coverageAmount * insuranceRate;
    }
}