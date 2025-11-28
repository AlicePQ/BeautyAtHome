package main.java.com.beautyathome.domain.pricing;


import main.java.com.beautyathome.domain.client.Client;
import main.java.com.beautyathome.domain.service.ServiceComponent;

public interface PricingStrategy {

    double calculatePrice(double basePrice, Client client, ServiceComponent service);
}
