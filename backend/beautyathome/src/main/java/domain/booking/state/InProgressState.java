package domain.booking.state;

import domain.booking.Booking;

public class InProgressState implements BookingState {

	@Override
	public void confirm(Booking booking) {
		// Already started
	}

	@Override
	public void start(Booking booking) {
		// Already in progress
	}

	@Override
	public void complete(Booking booking) {
		booking.setState(new CompletedState());
		booking.notifyObservers();
	}

	@Override
	public void cancel(Booking booking) {
		booking.setState(new CancelledState());
		booking.notifyObservers();
	}
}
