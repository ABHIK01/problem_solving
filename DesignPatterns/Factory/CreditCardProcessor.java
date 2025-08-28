package DesignPatterns.Factory;

/**
 * Credit Card Payment Processor Implementation
 */
public class CreditCardProcessor implements PaymentProcessor {
    
    private String cardNumber;
    private String cvv;
    
    public CreditCardProcessor(String cardNumber, String cvv) {
        this.cardNumber = maskCardNumber(cardNumber);
        this.cvv = cvv;
    }
    
    @Override
    public boolean processPayment(double amount, String currency) {
        System.out.println("Processing credit card payment...");
        System.out.println("Card: " + cardNumber);
        System.out.println("Amount: " + amount + " " + currency);
        System.out.println("Transaction fee: " + getTransactionFee(amount) + " " + currency);
        
        // Simulate payment processing
        try {
            Thread.sleep(200);
            System.out.println("Credit card payment successful!");
            return true;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
    }
    
    @Override
    public String getPaymentMethod() {
        return "Credit Card";
    }
    
    @Override
    public double getTransactionFee(double amount) {
        return amount * 0.029; // 2.9% fee for credit cards
    }
    
    private String maskCardNumber(String cardNumber) {
        if (cardNumber.length() >= 4) {
            return "**** **** **** " + cardNumber.substring(cardNumber.length() - 4);
        }
        return "****";
    }
}