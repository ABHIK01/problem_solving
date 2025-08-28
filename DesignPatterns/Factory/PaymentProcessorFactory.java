package DesignPatterns.Factory;

/**
 * Factory Pattern Implementation
 * PaymentProcessorFactory creates different payment processors based on payment type
 */
public class PaymentProcessorFactory {
    
    public enum PaymentType {
        CREDIT_CARD,
        PAYPAL,
        BANK_TRANSFER
    }
    
    /**
     * Factory method to create payment processors based on type
     * @param type Payment type enum
     * @param details Payment details (varies by type)
     * @return Appropriate PaymentProcessor implementation
     */
    public static PaymentProcessor createPaymentProcessor(PaymentType type, String... details) {
        switch (type) {
            case CREDIT_CARD:
                if (details.length >= 2) {
                    return new CreditCardProcessor(details[0], details[1]); // cardNumber, cvv
                }
                throw new IllegalArgumentException("Credit card requires card number and CVV");
                
            case PAYPAL:
                if (details.length >= 1) {
                    return new PayPalProcessor(details[0]); // email
                }
                throw new IllegalArgumentException("PayPal requires email address");
                
            case BANK_TRANSFER:
                if (details.length >= 2) {
                    return new BankTransferProcessor(details[0], details[1]); // accountNumber, routingNumber
                }
                throw new IllegalArgumentException("Bank transfer requires account number and routing number");
                
            default:
                throw new IllegalArgumentException("Unsupported payment type: " + type);
        }
    }
    
    /**
     * Convenience method to get available payment types
     */
    public static PaymentType[] getAvailablePaymentTypes() {
        return PaymentType.values();
    }
    
    /**
     * Get payment type from string (case-insensitive)
     */
    public static PaymentType getPaymentTypeFromString(String typeString) {
        try {
            return PaymentType.valueOf(typeString.toUpperCase().replace(" ", "_"));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid payment type: " + typeString);
        }
    }
}