package main.java.com.beautyathome.domain.service.decorator;

import main.java.com.beautyathome.domain.service.ServiceComponent;

public class PremiumAddon extends ServiceDecorator {

    private double extraPrice;

    public PremiumAddon(ServiceComponent component, double extraPrice) {
        super(component);
        this.extraPrice = extraPrice;
    }

    @Override
    public String getName() {
        return component.getName() + " (Premium)";
    }

    @Override
    public double getPrice() {
        return component.getPrice() + extraPrice;
    }
}
