package DesignPatterns.Factory;

/**
 * Bank Transfer Payment Processor Implementation
 */
public class BankTransferProcessor implements PaymentProcessor {
    
    private String accountNumber;
    private String routingNumber;
    
    public BankTransferProcessor(String accountNumber, String routingNumber) {
        this.accountNumber = maskAccountNumber(accountNumber);
        this.routingNumber = routingNumber;
    }
    
    @Override
    public boolean processPayment(double amount, String currency) {
        System.out.println("Processing bank transfer payment...");
        System.out.println("Account: " + accountNumber);
        System.out.println("Routing: " + routingNumber);
        System.out.println("Amount: " + amount + " " + currency);
        System.out.println("Transaction fee: " + getTransactionFee(amount) + " " + currency);
        
        // Simulate payment processing (bank transfers take longer)
        try {
            Thread.sleep(500);
            System.out.println("Bank transfer payment successful!");
            return true;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
    }
    
    @Override
    public String getPaymentMethod() {
        return "Bank Transfer";
    }
    
    @Override
    public double getTransactionFee(double amount) {
        return Math.max(1.00, amount * 0.005); // 0.5% fee, minimum $1.00
    }
    
    private String maskAccountNumber(String accountNumber) {
        if (accountNumber.length() >= 4) {
            return "****" + accountNumber.substring(accountNumber.length() - 4);
        }
        return "****";
    }
}