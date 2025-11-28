package main.java.com.beautyathome.domain.booking.observer;

import main.java.com.beautyathome.domain.booking.Booking;

public interface NotificationObserver {

    void update(Booking booking);
}
