package DesignPatterns.Demo;

import DesignPatterns.Singleton.DatabaseConnectionManager;
import DesignPatterns.Factory.PaymentProcessor;
import DesignPatterns.Factory.PaymentProcessorFactory;
import DesignPatterns.Factory.PaymentProcessorFactory.PaymentType;
import DesignPatterns.Builder.Order;
import DesignPatterns.Builder.OrderBuilder;

import java.time.LocalDateTime;

/**
 * Demonstration of Factory, Builder, and Singleton patterns working together
 * in an E-commerce Order Management System
 */
public class ECommerceDemo {
    
    public static void main(String[] args) {
        System.out.println("=== E-commerce Order Management System Demo ===");
        System.out.println("Demonstrating Factory, Builder, and Singleton Design Patterns\n");
        
        // Demonstrate multiple scenarios
        demonstrateBasicOrder();
        System.out.println("\n" + "=".repeat(60) + "\n");
        
        demonstrateGiftOrder();
        System.out.println("\n" + "=".repeat(60) + "\n");
        
        demonstrateExpressOrder();
        System.out.println("\n" + "=".repeat(60) + "\n");
        
        demonstrateSingletonBehavior();
    }
    
    /**
     * Demonstrate basic order processing with credit card payment
     */
    private static void demonstrateBasicOrder() {
        System.out.println("📦 SCENARIO 1: Basic Order with Credit Card Payment");
        System.out.println("-".repeat(50));
        
        // BUILDER PATTERN: Create a complex order with optional parameters
        Order order = Order.builder()
                .customerId("CUST-12345")
                .addItem("PROD-001", "Wireless Headphones", 1, 199.99)
                .addItem("PROD-002", "Phone Case", 2, 24.99)
                .shippingAddress("123 Main St, Anytown, ST 12345")
                .billingAddress("123 Main St, Anytown, ST 12345")
                .discountCode("SAVE10")
                .customerNotes("Please leave package at door")
                .build();
        
        System.out.println("Order created using Builder Pattern:");
        System.out.println("Order ID: " + order.getOrderId());
        System.out.println("Customer: " + order.getCustomerId());
        System.out.println("Items: " + order.getItems().size());
        System.out.println("Total Amount: $" + String.format("%.2f", order.getTotalAmount()));
        System.out.println();
        
        // FACTORY PATTERN: Create payment processor based on type
        PaymentProcessor paymentProcessor = PaymentProcessorFactory.createPaymentProcessor(
            PaymentType.CREDIT_CARD, 
            "1234567890123456", 
            "123"
        );
        
        System.out.println("Payment processor created using Factory Pattern:");
        System.out.println("Payment Method: " + paymentProcessor.getPaymentMethod());
        
        // Process payment
        boolean paymentSuccess = paymentProcessor.processPayment(
            order.getTotalAmount(), 
            "USD"
        );
        System.out.println();
        
        // SINGLETON PATTERN: Save order and payment to database
        DatabaseConnectionManager dbManager = DatabaseConnectionManager.getInstance();
        System.out.println("Database connection using Singleton Pattern:");
        System.out.println(dbManager.getConnectionInfo());
        
        if (paymentSuccess) {
            dbManager.saveOrder(order.getOrderId() + ", " + order.getCustomerId() + ", " + order.getTotalAmount());
            dbManager.savePayment("PAYMENT-" + order.getOrderId() + ", " + paymentProcessor.getPaymentMethod());
            System.out.println("✅ Order and payment saved successfully!");
        } else {
            System.out.println("❌ Payment failed, order not saved.");
        }
    }
    
    /**
     * Demonstrate gift order with PayPal payment
     */
    private static void demonstrateGiftOrder() {
        System.out.println("🎁 SCENARIO 2: Gift Order with PayPal Payment");
        System.out.println("-".repeat(50));
        
        // BUILDER PATTERN: Create gift order with gift wrapping
        Order giftOrder = Order.builder()
                .customerId("CUST-67890")
                .addItem("PROD-003", "Smart Watch", 1, 299.99)
                .shippingAddress("456 Oak Ave, Somewhere, ST 67890")
                .billingAddress("789 Pine St, Elsewhere, ST 11111")
                .giftWrap(true)
                .giftMessage("Happy Birthday! Hope you love this!")
                .customerNotes("This is a surprise gift - please be discreet")
                .build();
        
        System.out.println("Gift order created using Builder Pattern:");
        System.out.println("Order ID: " + giftOrder.getOrderId());
        System.out.println("Gift Wrap: " + (giftOrder.isGiftWrap() ? "Yes" : "No"));
        System.out.println("Gift Message: " + giftOrder.getGiftMessage());
        System.out.println("Total Amount: $" + String.format("%.2f", giftOrder.getTotalAmount()));
        System.out.println();
        
        // FACTORY PATTERN: Create PayPal processor
        PaymentProcessor paypalProcessor = PaymentProcessorFactory.createPaymentProcessor(
            PaymentType.PAYPAL, 
            "user@example.com"
        );
        
        System.out.println("PayPal processor created using Factory Pattern:");
        boolean paymentSuccess = paypalProcessor.processPayment(
            giftOrder.getTotalAmount(), 
            "USD"
        );
        System.out.println();
        
        // SINGLETON PATTERN: Same database instance
        DatabaseConnectionManager dbManager = DatabaseConnectionManager.getInstance();
        if (paymentSuccess) {
            dbManager.saveOrder(giftOrder.getOrderId() + ", " + giftOrder.getCustomerId() + ", " + giftOrder.getTotalAmount());
            dbManager.savePayment("PAYMENT-" + giftOrder.getOrderId() + ", " + paypalProcessor.getPaymentMethod());
            System.out.println("✅ Gift order and payment saved successfully!");
        }
    }
    
    /**
     * Demonstrate express order with bank transfer
     */
    private static void demonstrateExpressOrder() {
        System.out.println("🚀 SCENARIO 3: Express Order with Bank Transfer");
        System.out.println("-".repeat(50));
        
        // BUILDER PATTERN: Create express order
        Order expressOrder = Order.builder()
                .customerId("CUST-99999")
                .addItem("PROD-004", "Gaming Laptop", 1, 1299.99)
                .addItem("PROD-005", "Gaming Mouse", 1, 79.99)
                .shippingAddress("999 Tech Blvd, Silicon Valley, CA 94000")
                .expressShipping(true)
                .deliveryDate(LocalDateTime.now().plusDays(1))
                .shippingMethod("Overnight Express")
                .customerNotes("Urgent delivery needed for presentation tomorrow")
                .build();
        
        System.out.println("Express order created using Builder Pattern:");
        System.out.println("Order ID: " + expressOrder.getOrderId());
        System.out.println("Express Shipping: " + (expressOrder.isExpressShipping() ? "Yes" : "No"));
        System.out.println("Delivery Date: " + expressOrder.getDeliveryDate());
        System.out.println("Total Amount: $" + String.format("%.2f", expressOrder.getTotalAmount()));
        System.out.println();
        
        // FACTORY PATTERN: Create bank transfer processor
        PaymentProcessor bankProcessor = PaymentProcessorFactory.createPaymentProcessor(
            PaymentType.BANK_TRANSFER, 
            "9876543210", 
            "123456789"
        );
        
        System.out.println("Bank transfer processor created using Factory Pattern:");
        boolean paymentSuccess = bankProcessor.processPayment(
            expressOrder.getTotalAmount(), 
            "USD"
        );
        System.out.println();
        
        // SINGLETON PATTERN: Same database instance
        DatabaseConnectionManager dbManager = DatabaseConnectionManager.getInstance();
        if (paymentSuccess) {
            dbManager.saveOrder(expressOrder.getOrderId() + ", " + expressOrder.getCustomerId() + ", " + expressOrder.getTotalAmount());
            dbManager.savePayment("PAYMENT-" + expressOrder.getOrderId() + ", " + bankProcessor.getPaymentMethod());
            System.out.println("✅ Express order and payment saved successfully!");
        }
    }
    
    /**
     * Demonstrate that DatabaseConnectionManager is truly a singleton
     */
    private static void demonstrateSingletonBehavior() {
        System.out.println("🔒 SINGLETON PATTERN VERIFICATION");
        System.out.println("-".repeat(50));
        
        // Get multiple instances
        DatabaseConnectionManager instance1 = DatabaseConnectionManager.getInstance();
        DatabaseConnectionManager instance2 = DatabaseConnectionManager.getInstance();
        DatabaseConnectionManager instance3 = DatabaseConnectionManager.getInstance();
        
        // Verify they're the same instance
        System.out.println("Instance 1 hashCode: " + instance1.hashCode());
        System.out.println("Instance 2 hashCode: " + instance2.hashCode());
        System.out.println("Instance 3 hashCode: " + instance3.hashCode());
        
        System.out.println("All instances are the same object: " + 
                          (instance1 == instance2 && instance2 == instance3));
        
        System.out.println("\n📊 DESIGN PATTERNS SUMMARY:");
        System.out.println("✅ Singleton: Ensured single database connection manager");
        System.out.println("✅ Factory: Created different payment processors based on type");
        System.out.println("✅ Builder: Constructed complex orders with optional parameters");
        System.out.println("\nAll patterns work together seamlessly in this e-commerce system!");
    }
}