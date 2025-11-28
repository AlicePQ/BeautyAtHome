package domain.booking.observer;

public interface BookingSubject {

    void attach(NotificationObserver observer);

    void detach(NotificationObserver observer);

    void notifyObservers();
}
