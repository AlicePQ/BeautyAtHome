package domain.service.decorator;

import domain.service.ServiceComponent;

public class DiscountAddon extends ServiceDecorator {

	private final double discountPercentage;

	public DiscountAddon(ServiceComponent component, double discountPercentage) {
		super(component);
		this.discountPercentage = discountPercentage;
	}

	@Override
	public String getName() {
		return component.getName() + " (Discount)";
	}

	@Override
	public double getPrice() {
		double base = component.getPrice();
		return base - (base * (discountPercentage / 100.0));
	}
}
