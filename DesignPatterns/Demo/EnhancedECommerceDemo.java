package DesignPatterns.Demo;

// Existing imports
import DesignPatterns.Singleton.DatabaseConnectionManager;
import DesignPatterns.Factory.*;
import DesignPatterns.Factory.PaymentProcessorFactory.PaymentType;
import DesignPatterns.Builder.*;

// New pattern imports
import DesignPatterns.Observer.*;
import DesignPatterns.Strategy.*;
import DesignPatterns.Decorator.*;

/**
 * Enhanced E-commerce demonstration showcasing six design patterns:
 * 1. Singleton Pattern - Database Connection Manager
 * 2. Factory Pattern - Payment Processor Factory
 * 3. Builder Pattern - Order Builder
 * 4. Observer Pattern - Order Status Notifications
 * 5. Strategy Pattern - Shipping Cost Calculation
 * 6. Decorator Pattern - Order Service Enhancements
 */
public class EnhancedECommerceDemo {
    
    public static void main(String[] args) {
        System.out.println("=== ENHANCED E-COMMERCE ORDER MANAGEMENT SYSTEM ===");
        System.out.println("Demonstrating SIX Design Patterns Working Together");
        System.out.println();
        
        // Scenario 1: Standard Order with Notifications
        standardOrderWithNotifications();
        
        System.out.println("============================================================");
        System.out.println();
        
        // Scenario 2: International Order with Multiple Shipping Options
        internationalOrderWithShippingStrategies();
        
        System.out.println("============================================================");
        System.out.println();
        
        // Scenario 3: Premium Order with Full Service Enhancements
        premiumOrderWithDecorators();
        
        System.out.println("============================================================");
        System.out.println();
        
        // Pattern Summary
        designPatternsSummary();
    }
    
    /**
     * Scenario 1: Demonstrates Observer Pattern with order notifications
     */
    private static void standardOrderWithNotifications() {
        System.out.println("📦 SCENARIO 1: Standard Order with Status Notifications");
        System.out.println("--------------------------------------------------");
        System.out.println("Patterns: Builder → Factory → Singleton → Observer");
        System.out.println();
        
        // 1. Build order using Builder Pattern
        Order order = Order.builder()
            .customerId("CUST-12345")
            .customerEmail("john.doe@example.com")
            .addItem("PROD-001", "Wireless Headphones", 1, 199.99)
            .addItem("PROD-002", "Phone Case", 2, 12.50)
            .shippingAddress("123 Main St, Anytown, ST 12345")
            .discountCode("SAVE5")
            .build();
        
        System.out.println("✅ Order built using Builder Pattern:");
        System.out.println("Order ID: " + order.getOrderId());
        System.out.println("Total: $" + String.format("%.2f", order.getTotalAmount()));
        System.out.println();
        
        // 2. Set up Observer Pattern for notifications
        OrderStatusManager statusManager = new OrderStatusManager(order.getOrderId(), order.getCustomerEmail());
        
        // Add different notification observers
        statusManager.addObserver(new EmailNotifier("SendGrid"));
        statusManager.addObserver(new SMSNotifier("Twilio"));
        statusManager.addObserver(new PushNotifier("ShopApp"));
        System.out.println();
        
        // 3. Process payment using Factory Pattern
        PaymentProcessor processor = PaymentProcessorFactory.createPaymentProcessor(
            PaymentType.CREDIT_CARD, "4532123456789012", "123"
        );
        
        System.out.println("💳 Payment processor created using Factory Pattern:");
        boolean paymentSuccess = processor.processPayment(order.getTotalAmount(), "USD");
        System.out.println("Payment successful: " + paymentSuccess);
        System.out.println();
        
        // 4. Save to database using Singleton Pattern
        DatabaseConnectionManager dbManager = DatabaseConnectionManager.getInstance();
        System.out.println("💾 Database operations using Singleton Pattern:");
        dbManager.saveOrder("Order: " + order.getOrderId());
        dbManager.savePayment("Payment for: " + order.getOrderId());
        System.out.println();
        
        // 5. Trigger status updates using Observer Pattern
        statusManager.updateStatus("CONFIRMED");
        statusManager.updateStatus("SHIPPED");
        statusManager.updateStatus("DELIVERED");
    }
    
    /**
     * Scenario 2: Demonstrates Strategy Pattern with different shipping options
     */
    private static void internationalOrderWithShippingStrategies() {
        System.out.println("🌍 SCENARIO 2: International Order with Shipping Strategies");
        System.out.println("--------------------------------------------------");
        System.out.println("Patterns: Builder → Strategy → Factory → Observer");
        System.out.println();
        
        // 1. Build international order
        Order order = Order.builder()
            .customerId("CUST-INTL-001")
            .customerEmail("maria.garcia@example.es")
            .addItem("PROD-100", "Laptop Computer", 1, 1299.99)
            .addItem("PROD-101", "Wireless Mouse", 1, 79.99)
            .shippingAddress("Calle Mayor 15, Madrid, Spain")
            .billingAddress("Same as shipping")
            .build();
        
        System.out.println("✅ International order built:");
        System.out.println("Order ID: " + order.getOrderId());
        System.out.println("Total: $" + String.format("%.2f", order.getTotalAmount()));
        System.out.println();
        
        // 2. Demonstrate Strategy Pattern with different shipping methods
        double weight = 5.2; // pounds
        double distance = 4500; // miles (US to Spain)
        boolean isInternational = true;
        
        System.out.println("📦 Shipping calculations using Strategy Pattern:");
        System.out.println("Package: " + weight + " lbs, Distance: " + distance + " miles");
        System.out.println();
        
        // Compare different shipping strategies
        ShippingCalculator calculator = new ShippingCalculator(new StandardShipping());
        ShippingCalculator.ShippingQuote quote1 = calculator.generateQuote(weight, distance, isInternational);
        System.out.println(quote1);
        System.out.println();
        
        calculator.setStrategy(new ExpressShipping());
        ShippingCalculator.ShippingQuote quote2 = calculator.generateQuote(weight, distance, isInternational);
        System.out.println(quote2);
        System.out.println();
        
        calculator.setStrategy(new InternationalShipping(order.getTotalAmount()));
        ShippingCalculator.ShippingQuote quote3 = calculator.generateQuote(weight, distance, isInternational);
        System.out.println(quote3);
        System.out.println();
        
        // 3. Process payment with PayPal (Factory Pattern)
        PaymentProcessor processor = PaymentProcessorFactory.createPaymentProcessor(
            PaymentType.PAYPAL, "maria.garcia@example.es", null
        );
        
        System.out.println("💳 PayPal payment processing (Factory Pattern):");
        boolean paymentSuccess = processor.processPayment(order.getTotalAmount() + quote3.getCost(), "USD");
        System.out.println("Payment successful: " + paymentSuccess);
        System.out.println();
        
        // 4. Set up international notifications (Observer Pattern)
        OrderStatusManager statusManager = new OrderStatusManager(order.getOrderId(), order.getCustomerEmail());
        statusManager.addObserver(new EmailNotifier("International Gateway"));
        statusManager.addObserver(new SMSNotifier("Global SMS"));
        System.out.println();
        
        statusManager.updateStatus("CUSTOMS_PROCESSING");
        statusManager.updateStatus("IN_TRANSIT_INTERNATIONAL");
    }
    
    /**
     * Scenario 3: Demonstrates Decorator Pattern with multiple service enhancements
     */
    private static void premiumOrderWithDecorators() {
        System.out.println("⭐ SCENARIO 3: Premium Order with Service Enhancements");
        System.out.println("--------------------------------------------------");
        System.out.println("Patterns: Builder → Decorator → Strategy → Factory → Observer");
        System.out.println();
        
        // 1. Build premium order
        Order order = Order.builder()
            .customerId("CUST-PREMIUM-001")
            .customerEmail("premium.customer@example.com")
            .addItem("PROD-LUXURY-001", "Diamond Watch", 1, 5999.99)
            .addItem("PROD-LUXURY-002", "Silk Scarf", 1, 299.99)
            .shippingAddress("456 Luxury Ave, Beverly Hills, CA 90210")
            .giftWrap(true)
            .giftMessage("Happy Anniversary!")
            .expressShipping(true)
            .build();
        
        System.out.println("✅ Premium order built:");
        System.out.println("Order ID: " + order.getOrderId());
        System.out.println("Base Total: $" + String.format("%.2f", order.getTotalAmount()));
        System.out.println();
        
        // 2. Apply multiple service enhancements using Decorator Pattern
        System.out.println("🎨 Applying service enhancements using Decorator Pattern:");
        
        // Start with basic order service
        OrderService orderService = new BasicOrderService();
        System.out.println("Base Service: " + orderService.getServiceDescription());
        
        // Add insurance coverage
        orderService = new InsuranceDecorator(orderService, order.getTotalAmount());
        System.out.println("+ Insurance: " + orderService.getServiceDescription());
        
        // Add premium tracking
        orderService = new TrackingDecorator(orderService, "premium");
        System.out.println("+ Tracking: " + orderService.getServiceDescription());
        
        // Add critical priority processing
        orderService = new PriorityDecorator(orderService, "critical");
        System.out.println("+ Priority: " + orderService.getServiceDescription());
        
        // Add luxury gift wrapping
        orderService = new GiftWrapDecorator(orderService, "luxury", "Happy Anniversary, my love!");
        System.out.println("+ Gift Wrap: " + orderService.getServiceDescription());
        System.out.println();
        
        double totalWithEnhancements = orderService.calculateTotalCost(order.getTotalAmount());
        System.out.println("💰 Total cost with enhancements: $" + String.format("%.2f", totalWithEnhancements));
        System.out.println("Enhancement cost: $" + String.format("%.2f", totalWithEnhancements - order.getTotalAmount()));
        System.out.println();
        
        // 3. Calculate overnight shipping using Strategy Pattern
        System.out.println("🚀 Overnight shipping calculation (Strategy Pattern):");
        ShippingCalculator shippingCalc = new ShippingCalculator(new OvernightShipping());
        try {
            ShippingCalculator.ShippingQuote quote = shippingCalc.generateQuote(2.5, 350, false);
            System.out.println(quote);
            totalWithEnhancements += quote.getCost();
        } catch (Exception e) {
            System.out.println("❌ " + e.getMessage());
            // Fallback to express shipping
            shippingCalc.setStrategy(new ExpressShipping());
            ShippingCalculator.ShippingQuote quote = shippingCalc.generateQuote(2.5, 350, false);
            System.out.println("Fallback: " + quote);
            totalWithEnhancements += quote.getCost();
        }
        System.out.println();
        
        // 4. Process order with all enhancements
        System.out.println("⚡ Processing enhanced order (Decorator Pattern):");
        String processingResult = orderService.processOrder(order.getOrderId());
        System.out.println(processingResult);
        System.out.println();
        
        // 5. Premium payment processing (Factory Pattern)
        PaymentProcessor processor = PaymentProcessorFactory.createPaymentProcessor(
            PaymentType.CREDIT_CARD, "5555444433332222", "456"
        );
        
        System.out.println("💳 Premium payment processing:");
        boolean paymentSuccess = processor.processPayment(totalWithEnhancements, "USD");
        System.out.println("Final total paid: $" + String.format("%.2f", totalWithEnhancements));
        System.out.println("Payment successful: " + paymentSuccess);
        System.out.println();
        
        // 6. VIP notifications (Observer Pattern)
        OrderStatusManager statusManager = new OrderStatusManager(order.getOrderId(), order.getCustomerEmail());
        statusManager.addObserver(new EmailNotifier("VIP Service"));
        statusManager.addObserver(new SMSNotifier("Premium SMS"));
        statusManager.addObserver(new PushNotifier("VIP App"));
        System.out.println();
        
        statusManager.updateStatus("VIP_PROCESSING");
        statusManager.updateStatus("PRIORITY_SHIPPED");
    }
    
    /**
     * Summary of all design patterns demonstrated
     */
    private static void designPatternsSummary() {
        System.out.println("📊 DESIGN PATTERNS SUMMARY");
        System.out.println("--------------------------------------------------");
        System.out.println("✅ Singleton Pattern: Database Connection Manager");
        System.out.println("   - Ensures single database connection instance");
        System.out.println("   - Thread-safe implementation");
        System.out.println();
        
        System.out.println("✅ Factory Pattern: Payment Processor Factory");
        System.out.println("   - Creates payment processors based on type");
        System.out.println("   - Supports Credit Card, PayPal, Bank Transfer");
        System.out.println();
        
        System.out.println("✅ Builder Pattern: Order Builder");
        System.out.println("   - Constructs complex orders with fluent interface");
        System.out.println("   - Handles optional parameters elegantly");
        System.out.println();
        
        System.out.println("✅ Observer Pattern: Order Status Manager");
        System.out.println("   - Notifies multiple channels (Email, SMS, Push)");
        System.out.println("   - Decouples notification logic from order processing");
        System.out.println();
        
        System.out.println("✅ Strategy Pattern: Shipping Calculator");
        System.out.println("   - Multiple shipping algorithms (Standard, Express, Overnight, International)");
        System.out.println("   - Runtime strategy switching");
        System.out.println();
        
        System.out.println("✅ Decorator Pattern: Order Service Enhancements");
        System.out.println("   - Adds services dynamically (Insurance, Tracking, Priority, Gift Wrap)");
        System.out.println("   - Chainable enhancements with cost calculation");
        System.out.println();
        
        System.out.println("🎯 All six patterns work together seamlessly in this comprehensive e-commerce system!");
        
        // Verify Singleton pattern one more time
        DatabaseConnectionManager instance1 = DatabaseConnectionManager.getInstance();
        DatabaseConnectionManager instance2 = DatabaseConnectionManager.getInstance();
        System.out.println();
        System.out.println("🔒 Singleton Verification:");
        System.out.println("Same instance: " + (instance1 == instance2));
        System.out.println("Instance hashCode: " + instance1.hashCode());
    }
}