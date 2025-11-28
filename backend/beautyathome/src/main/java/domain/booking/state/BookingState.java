package domain.booking.state;

import domain.booking.Booking;

public interface BookingState {

    void confirm(Booking booking);
    void start(Booking booking);
    void complete(Booking booking);
    void cancel(Booking booking);
}
