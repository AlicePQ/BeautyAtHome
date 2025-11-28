package domain.service.decorator;

import domain.service.ServiceComponent;

public class PremiumAddon extends ServiceDecorator {

    private final double extraPrice;

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
