package main.java.com.beautyathome.domain.booking.state;

import main.java.com.beautyathome.domain.booking.Booking;

public class ConfirmedState implements BookingState {

	@Override
	public void confirm(Booking booking) {
		// Already confirmed
	}

	@Override
	public void start(Booking booking) {
		// Transition to in-progress might be implemented elsewhere
	}

	@Override
	public void complete(Booking booking) {
		// Not allowed directly from confirmed
	}

	@Override
	public void cancel(Booking booking) {
		booking.setState(new CancelledState());
		booking.notifyObservers();
	}
}
