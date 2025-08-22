package DesignPatterns.Factory;

/**
 * Payment Processor Interface
 * Common interface for all payment processors
 */
public interface PaymentProcessor {
    boolean processPayment(double amount, String currency);
    String getPaymentMethod();
    double getTransactionFee(double amount);
}