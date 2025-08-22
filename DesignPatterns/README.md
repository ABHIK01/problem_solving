# Design Patterns Use Case: E-commerce Order Management System

This project demonstrates a practical implementation of three fundamental design patterns in Java: **Factory**, **Builder**, and **Singleton** patterns, working together in an E-commerce Order Management System.

## Overview

The e-commerce system showcases how these design patterns solve real-world problems and work cohesively:

- **Singleton Pattern**: Database Connection Manager (ensures single connection instance)
- **Factory Pattern**: Payment Processor Factory (creates different payment processors)
- **Builder Pattern**: Order Builder (constructs complex order objects)

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
└── Demo/
    └── ECommerceDemo.java
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
java DesignPatterns.Demo.ECommerceDemo
```

## Demo Scenarios

The demo showcases three different scenarios:

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

## Key Features

### Order Builder Features
- **Required fields**: Customer ID, at least one item
- **Optional fields**: Shipping/billing addresses, discount codes, gift options, shipping preferences
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

## Educational Value

This implementation teaches:
- When and why to use each design pattern
- How patterns can work together effectively
- Real-world scenarios where these patterns shine
- Best practices for pattern implementation
- Thread-safety considerations
- Object-oriented design principles

## Extension Possibilities

The codebase can be easily extended with:
- New payment processors (Apple Pay, Google Pay, etc.)
- Additional order types (subscription orders, pre-orders)
- Enhanced database operations (complex queries, transactions)
- Logging and monitoring capabilities
- Configuration management
- Error handling and retry mechanisms

This comprehensive example demonstrates that design patterns are not just academic concepts but practical tools that solve real problems in software development.