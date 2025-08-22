package DesignPatterns.Factory;

/**
 * PayPal Payment Processor Implementation
 */
public class PayPalProcessor implements PaymentProcessor {
    
    private String email;
    
    public PayPalProcessor(String email) {
        this.email = email;
    }
    
    @Override
    public boolean processPayment(double amount, String currency) {
        System.out.println("Processing PayPal payment...");
        System.out.println("PayPal account: " + email);
        System.out.println("Amount: " + amount + " " + currency);
        System.out.println("Transaction fee: " + getTransactionFee(amount) + " " + currency);
        
        // Simulate payment processing
        try {
            Thread.sleep(150);
            System.out.println("PayPal payment successful!");
            return true;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
    }
    
    @Override
    public String getPaymentMethod() {
        return "PayPal";
    }
    
    @Override
    public double getTransactionFee(double amount) {
        return amount * 0.034 + 0.30; // 3.4% + $0.30 fee for PayPal
    }
}