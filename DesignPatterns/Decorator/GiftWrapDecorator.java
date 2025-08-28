package DesignPatterns.Decorator;

/**
 * Concrete decorator that adds gift wrapping service to orders.
 * Provides beautiful presentation for gift orders.
 */
public class GiftWrapDecorator extends OrderServiceDecorator {
    private String wrapStyle;
    private String giftMessage;
    private double giftWrapFee;
    
    public GiftWrapDecorator(OrderService orderService, String wrapStyle, String giftMessage) {
        super(orderService);
        this.wrapStyle = wrapStyle;
        this.giftMessage = giftMessage;
        
        // Set gift wrap fee based on style
        switch (wrapStyle.toLowerCase()) {
            case "standard":
                this.giftWrapFee = 4.99;
                break;
            case "premium":
                this.giftWrapFee = 8.99;
                break;
            case "luxury":
                this.giftWrapFee = 15.99;
                break;
            default:
                this.giftWrapFee = 4.99;
                this.wrapStyle = "standard";
        }
    }
    
    @Override
    public String processOrder(String orderId) {
        String result = super.processOrder(orderId);
        return result + "\n🎁 " + capitalizeFirst(wrapStyle) + " gift wrapping applied to order " + 
               orderId + (giftMessage != null ? " with message: \"" + giftMessage + "\"" : "");
    }
    
    @Override
    public double calculateTotalCost(double baseOrderCost) {
        double baseCost = super.calculateTotalCost(baseOrderCost);
        return baseCost + giftWrapFee;
    }
    
    @Override
    public String getServiceDescription() {
        return super.getServiceDescription() + " + " + capitalizeFirst(wrapStyle) + 
               " Gift Wrapping ($" + String.format("%.2f", giftWrapFee) + ")";
    }
    
    private String capitalizeFirst(String str) {
        if (str == null || str.isEmpty()) return str;
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }
    
    public String getWrapStyle() {
        return wrapStyle;
    }
    
    public String getGiftMessage() {
        return giftMessage;
    }
    
    public double getGiftWrapFee() {
        return giftWrapFee;
    }
}