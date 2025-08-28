# Design Patterns Use Case: E-commerce Order Management System

This project demonstrates a practical implementation of **six fundamental design patterns** in Java: **Factory**, **Builder**, **Singleton**, **Observer**, **Strategy**, and **Decorator** patterns, working together in an E-commerce Order Management System.

## Overview

The e-commerce system showcases how these design patterns solve real-world problems and work cohesively:

- **Singleton Pattern**: Database Connection Manager (ensures single connection instance)
- **Factory Pattern**: Payment Processor Factory (creates different payment processors)
- **Builder Pattern**: Order Builder (constructs complex order objects)
- **Observer Pattern**: Order Status Manager (notifies multiple channels about status changes)
- **Strategy Pattern**: Shipping Calculator (different shipping cost calculation algorithms)
- **Decorator Pattern**: Order Service Enhancements (dynamically adds services to orders)

## Project Structure

```
DesignPatterns/
├── Singleton/
│   └── DatabaseConnectionManager.java
├── Factory/
│   ├── PaymentProcessor.java (interface)
│   ├── CreditCardProcessor.java
│   ├── PayPalProcessor.java
│   ├── BankTransferProcessor.java
│   └── PaymentProcessorFactory.java
├── Builder/
│   ├── Order.java
│   └── OrderBuilder.java
├── Observer/
│   ├── OrderStatusObserver.java (interface)
│   ├── EmailNotifier.java
│   ├── SMSNotifier.java
│   ├── PushNotifier.java
│   └── OrderStatusManager.java
├── Strategy/
│   ├── ShippingStrategy.java (interface)
│   ├── StandardShipping.java
│   ├── ExpressShipping.java
│   ├── OvernightShipping.java
│   ├── InternationalShipping.java
│   └── ShippingCalculator.java
├── Decorator/
│   ├── OrderService.java (interface)
│   ├── BasicOrderService.java
│   ├── OrderServiceDecorator.java (abstract)
│   ├── InsuranceDecorator.java
│   ├── TrackingDecorator.java
│   ├── PriorityDecorator.java
│   └── GiftWrapDecorator.java
└── Demo/
    ├── ECommerceDemo.java (original demo)
    └── EnhancedECommerceDemo.java (new comprehensive demo)
```

## Design Patterns Explained

### 1. Singleton Pattern - DatabaseConnectionManager

**Problem Solved**: Ensures only one database connection manager exists throughout the application lifecycle.

**Benefits**:
- Resource management and connection pooling
- Thread-safe implementation using double-checked locking
- Global access point for database operations

```java
DatabaseConnectionManager dbManager = DatabaseConnectionManager.getInstance();
dbManager.saveOrder(orderData);
```

### 2. Factory Pattern - Payment Processing

**Problem Solved**: Creates different payment processor objects based on payment type without exposing instantiation logic.

**Benefits**:
- Loose coupling between client code and concrete implementations
- Easy to add new payment methods
- Centralized object creation logic

```java
PaymentProcessor processor = PaymentProcessorFactory.createPaymentProcessor(
    PaymentType.CREDIT_CARD, "1234567890123456", "123"
);
```

**Supported Payment Types**:
- Credit Card (with transaction fees: 2.9%)
- PayPal (with transaction fees: 3.4% + $0.30)
- Bank Transfer (with transaction fees: 0.5%, minimum $1.00)

### 3. Builder Pattern - Order Construction

**Problem Solved**: Constructs complex Order objects with many optional parameters in a readable, flexible way.

**Benefits**:
- Fluent interface for readable code
- Step-by-step object construction
- Validation during the build process
- Immutable objects once built

```java
Order order = Order.builder()
    .customerId("CUST-12345")
    .addItem("PROD-001", "Wireless Headphones", 1, 199.99)
    .shippingAddress("123 Main St, Anytown, ST 12345")
    .discountCode("SAVE10")
    .giftWrap(true)
    .expressShipping(true)
    .build();
```

### 4. Observer Pattern - Order Status Notifications

**Problem Solved**: Notifies multiple channels (email, SMS, push notifications) when order status changes without tight coupling.

**Benefits**:
- Loose coupling between order processing and notification systems
- Easy to add/remove notification channels
- Real-time status updates to customers
- Scalable notification system

```java
OrderStatusManager statusManager = new OrderStatusManager(orderId, customerEmail);
statusManager.addObserver(new EmailNotifier("SendGrid"));
statusManager.addObserver(new SMSNotifier("Twilio"));
statusManager.updateStatus("SHIPPED");
```

**Supported Notification Channels**:
- Email notifications with detailed messaging
- SMS notifications with concise updates
- Push notifications for mobile apps

### 5. Strategy Pattern - Shipping Calculation

**Problem Solved**: Provides different shipping cost calculation algorithms that can be selected at runtime.

**Benefits**:
- Multiple shipping options with different pricing models
- Easy to add new shipping methods
- Runtime algorithm selection
- Centralized shipping logic

```java
ShippingCalculator calculator = new ShippingCalculator(new ExpressShipping());
ShippingQuote quote = calculator.generateQuote(weight, distance, isInternational);
calculator.setStrategy(new OvernightShipping()); // Switch algorithm
```

**Supported Shipping Strategies**:
- Standard Shipping (cost-effective, longer delivery)
- Express Shipping (faster delivery, higher cost)
- Overnight Shipping (next-day delivery, premium pricing)
- International Shipping (with customs and duty fees)

### 6. Decorator Pattern - Order Service Enhancements

**Problem Solved**: Adds optional services to orders dynamically without modifying the core order structure.

**Benefits**:
- Dynamic service addition at runtime
- Chainable enhancements
- Cost calculation with multiple services
- Open for extension, closed for modification

```java
OrderService service = new BasicOrderService();
service = new InsuranceDecorator(service, orderValue);
service = new TrackingDecorator(service, "premium");
service = new PriorityDecorator(service, "critical");
```

**Available Service Enhancements**:
- Insurance Coverage (protects against loss/damage)
- Package Tracking (basic, detailed, premium levels)
- Priority Processing (high, urgent, critical levels)
- Gift Wrapping (standard, premium, luxury styles)

## Running the Demo

### Prerequisites
- Java 8 or higher
- No external dependencies required

### Compilation
```bash
cd /path/to/project
find DesignPatterns -name "*.java" -exec javac {} \;
```

### Execution
```bash
# Original demo (3 patterns)
java DesignPatterns.Demo.ECommerceDemo

# Enhanced demo (6 patterns)
java DesignPatterns.Demo.EnhancedECommerceDemo
```

## Demo Scenarios

### Original Demo (ECommerceDemo.java)
The original demo showcases three different scenarios with basic patterns:

1. **Basic Order with Credit Card Payment**
   - Standard order processing
   - Credit card payment with transaction fees
   - Database persistence

2. **Gift Order with PayPal Payment**
   - Gift wrapping and messaging
   - PayPal payment processing
   - Special handling for gift orders

3. **Express Order with Bank Transfer**
   - Express shipping with delivery date
   - Bank transfer payment
   - Rush order processing

4. **Singleton Verification**
   - Demonstrates that multiple calls to `getInstance()` return the same object
   - Shows hash codes to verify object identity

### Enhanced Demo (EnhancedECommerceDemo.java)
The enhanced demo showcases six design patterns with comprehensive scenarios:

1. **Standard Order with Status Notifications**
   - Builder Pattern: Complex order construction
   - Factory Pattern: Payment processor creation
   - Singleton Pattern: Database operations
   - Observer Pattern: Multi-channel notifications (Email, SMS, Push)

2. **International Order with Shipping Strategies**
   - Builder Pattern: International order with special requirements
   - Strategy Pattern: Multiple shipping cost calculations
   - Factory Pattern: PayPal payment processing
   - Observer Pattern: International notification channels

3. **Premium Order with Service Enhancements**
   - Builder Pattern: Luxury order construction
   - Decorator Pattern: Chained service enhancements (Insurance + Tracking + Priority + Gift Wrap)
   - Strategy Pattern: Overnight shipping calculation
   - Factory Pattern: Premium payment processing
   - Observer Pattern: VIP notification channels

## Key Features

### Order Builder Features
- **Required fields**: Customer ID, at least one item
- **Optional fields**: Customer email, shipping/billing addresses, discount codes, gift options, shipping preferences
- **Automatic calculations**: Total amount, discounts, shipping fees
- **Validation**: Ensures required fields are present before building

### Payment Factory Features
- **Multiple payment types**: Credit Card, PayPal, Bank Transfer
- **Different fee structures**: Each payment type has realistic transaction fees
- **Extensible design**: Easy to add new payment methods
- **Error handling**: Validates required parameters for each payment type

### Database Singleton Features
- **Thread-safe**: Uses double-checked locking pattern
- **Connection simulation**: Simulates database connection establishment
- **Query execution**: Provides methods for saving orders and payments
- **Connection status**: Tracks and reports connection status

### Observer Pattern Features
- **Multi-channel notifications**: Email, SMS, and push notifications
- **Loose coupling**: Order processing independent of notification logic
- **Dynamic observer management**: Add/remove observers at runtime
- **Real-time updates**: Immediate notifications on status changes

### Strategy Pattern Features
- **Multiple algorithms**: Different shipping cost calculation methods
- **Runtime switching**: Change shipping strategy dynamically
- **Extensible design**: Easy to add new shipping methods
- **Cost optimization**: Compare different shipping options

### Decorator Pattern Features
- **Dynamic enhancement**: Add services to orders at runtime
- **Chainable decorators**: Combine multiple enhancements
- **Cost calculation**: Automatic cost updates with each enhancement
- **Service flexibility**: Enable/disable services as needed

## Real-World Applications

This pattern combination is commonly used in:

- **E-commerce platforms**: Order processing, payment handling
- **Banking systems**: Transaction processing, account management
- **Inventory management**: Product catalog, order fulfillment
- **Content management**: User preferences, content delivery

## Best Practices Demonstrated

1. **Immutable objects**: Orders are immutable once built
2. **Fluent interfaces**: Builder provides chainable methods
3. **Separation of concerns**: Each pattern has a specific responsibility
4. **Thread safety**: Singleton uses proper synchronization
5. **Validation**: Builder validates required fields
6. **Error handling**: Factory validates input parameters
7. **Loose coupling**: Observer pattern decouples notifications from core logic
8. **Open/Closed principle**: Decorator pattern allows extension without modification
9. **Strategy selection**: Runtime algorithm switching for optimal solutions
10. **Cost transparency**: Clear breakdown of all charges and enhancements

## Educational Value

This implementation teaches:
- When and why to use each design pattern
- How patterns can work together effectively
- Real-world scenarios where these patterns shine
- Best practices for pattern implementation
- Thread-safety considerations
- Object-oriented design principles
- Notification system architecture
- Dynamic service enhancement strategies
- Cost calculation with multiple variables
- Extensible software architecture design

## Extension Possibilities

The codebase can be easily extended with:
- New payment processors (Apple Pay, Google Pay, Cryptocurrency, etc.)
- Additional order types (subscription orders, pre-orders, marketplace orders)
- Enhanced database operations (complex queries, transactions, connection pooling)
- Advanced notification channels (Slack, Discord, Webhook integrations)
- More shipping strategies (drone delivery, same-day delivery, pickup points)
- Additional order enhancements (warranty, white-glove delivery, installation)
- Logging and monitoring capabilities (audit trails, performance metrics)
- Configuration management (feature flags, A/B testing)
- Error handling and retry mechanisms (circuit breakers, exponential backoff)
- Internationalization support (multi-currency, localization)

This comprehensive example demonstrates that design patterns are not just academic concepts but practical tools that solve real problems in software development. The implementation showcases six fundamental patterns working together to create a robust, extensible, and maintainable e-commerce system that can handle complex real-world requirements.