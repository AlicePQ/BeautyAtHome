package main.java.com.beautyathome.domain.pricing;


import main.java.com.beautyathome.domain.client.Client;
import main.java.com.beautyathome.domain.service.ServiceComponent;

public class StandardPricingStrategy implements PricingStrategy {

    @Override
    public double calculatePrice(double basePrice, Client client, ServiceComponent service) {
        return basePrice;
    }
}
