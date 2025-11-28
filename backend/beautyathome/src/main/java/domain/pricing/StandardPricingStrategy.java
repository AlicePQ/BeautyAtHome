package domain.pricing;


import domain.client.Client;
import domain.service.ServiceComponent;

public class StandardPricingStrategy implements PricingStrategy {

    @Override
    public double calculatePrice(double basePrice, Client client, ServiceComponent service) {
        return basePrice;
    }
}
