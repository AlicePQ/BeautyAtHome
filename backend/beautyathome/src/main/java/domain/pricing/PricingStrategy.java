package domain.pricing;


import domain.client.Client;
import domain.service.ServiceComponent;

public interface PricingStrategy {

    double calculatePrice(double basePrice, Client client, ServiceComponent service);
}
