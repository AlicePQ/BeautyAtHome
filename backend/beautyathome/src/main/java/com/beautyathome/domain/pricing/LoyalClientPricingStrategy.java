package main.java.com.beautyathome.domain.pricing;


import main.java.com.beautyathome.domain.client.Client;
import main.java.com.beautyathome.domain.service.ServiceComponent;

public class LoyalClientPricingStrategy implements PricingStrategy {

    @Override
    public double calculatePrice(double basePrice, Client client, ServiceComponent service) {
        // Ejemplo: 10% de descuento
        return basePrice * 0.9;
    }
}
