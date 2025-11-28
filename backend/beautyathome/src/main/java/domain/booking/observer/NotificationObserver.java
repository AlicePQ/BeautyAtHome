package domain.booking.observer;

import domain.booking.Booking;

public interface NotificationObserver {

    void update(Booking booking);
}
