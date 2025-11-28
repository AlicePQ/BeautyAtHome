package main.java.com.beautyathome.domain.booking;

import main.java.com.beautyathome.domain.booking.state.BookingState;
import main.java.com.beautyathome.domain.booking.observer.BookingSubject;
import main.java.com.beautyathome.domain.booking.observer.NotificationObserver;
import main.java.com.beautyathome.domain.booking.state.PendingState;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Booking implements BookingSubject {

    private String id;
    private String clientId;
    private String professionalId;
    private String serviceId;
    private LocalDateTime dateTime;

    private BookingState state;
    private final List<NotificationObserver> observers = new ArrayList<>();

    public Booking(String id, String clientId, String professionalId, String serviceId, LocalDateTime dateTime) {
        this.id = id;
        this.clientId = clientId;
        this.professionalId = professionalId;
        this.serviceId = serviceId;
        this.dateTime = dateTime;
        this.state = new PendingState();
    }

    // getters omitidos por brevedad

    public void setState(BookingState state) {
        this.state = state;
    }

    public void confirm() {
        state.confirm(this);
    }

    public void start() {
        state.start(this);
    }

    public void complete() {
        state.complete(this);
    }

    public void cancel() {
        state.cancel(this);
    }

    @Override
    public void attach(NotificationObserver observer) {
        observers.add(observer);
    }

    @Override
    public void detach(NotificationObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (NotificationObserver o : observers) {
            o.update(this);
        }
    }
}