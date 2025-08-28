package DesignPatterns.Singleton;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Singleton Pattern Implementation
 * DatabaseConnectionManager ensures only one instance manages database connections
 * Thread-safe implementation using double-checked locking
 */
public class DatabaseConnectionManager {
    
    private static volatile DatabaseConnectionManager instance;
    private AtomicBoolean isConnected;
    private String connectionString;
    
    // Private constructor prevents direct instantiation
    private DatabaseConnectionManager() {
        this.isConnected = new AtomicBoolean(false);
        this.connectionString = "jdbc:mysql://localhost:3306/ecommerce";
        initializeConnection();
    }
    
    /**
     * Thread-safe singleton instance creation using double-checked locking
     */
    public static DatabaseConnectionManager getInstance() {
        if (instance == null) {
            synchronized (DatabaseConnectionManager.class) {
                if (instance == null) {
                    instance = new DatabaseConnectionManager();
                }
            }
        }
        return instance;
    }
    
    private void initializeConnection() {
        // Simulate database connection initialization
        try {
            Thread.sleep(100); // Simulate connection time
            isConnected.set(true);
            System.out.println("Database connection established: " + connectionString);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            isConnected.set(false);
        }
    }
    
    public boolean executeQuery(String query) {
        if (!isConnected.get()) {
            System.out.println("Database not connected!");
            return false;
        }
        System.out.println("Executing query: " + query);
        return true;
    }
    
    public boolean saveOrder(String orderData) {
        return executeQuery("INSERT INTO orders VALUES (" + orderData + ")");
    }
    
    public boolean savePayment(String paymentData) {
        return executeQuery("INSERT INTO payments VALUES (" + paymentData + ")");
    }
    
    public boolean isConnected() {
        return isConnected.get();
    }
    
    public String getConnectionInfo() {
        return "Database Connection Manager - Connected: " + isConnected.get() + 
               ", Connection: " + connectionString;
    }
}