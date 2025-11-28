package domain.booking.state;


import domain.booking.Booking;

public class PendingState implements BookingState {

    @Override
    public void confirm(Booking booking) {
        booking.setState(new ConfirmedState());
        booking.notifyObservers();
    }

    @Override
    public void start(Booking booking) {
        // No permitido desde Pending
    }

    @Override
    public void complete(Booking booking) {
        // No permitido
    }

    @Override
    public void cancel(Booking booking) {
        booking.setState(new CancelledState());
        booking.notifyObservers();
    }
}
