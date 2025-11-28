package domain.service.decorator;

import domain.service.ServiceComponent;

public class TravelFeeAddon extends ServiceDecorator {

	private final double travelFee;

	public TravelFeeAddon(ServiceComponent component, double travelFee) {
		super(component);
		this.travelFee = travelFee;
	}

	@Override
	public String getName() {
		return component.getName() + " + Travel";
	}

	@Override
	public double getPrice() {
		return component.getPrice() + travelFee;
	}
}
