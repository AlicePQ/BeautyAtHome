package domain.booking;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import domain.booking.observer.BookingSubject;
import domain.booking.observer.NotificationObserver;
import domain.booking.state.BookingState;
import domain.booking.state.PendingState;

public class Booking implements BookingSubject {

    private final String id;
    private final String clientId;
    private final String professionalId;
    private final String serviceId;
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

    public String getId() {
        return id;
    }

    public String getClientId() {
        return clientId;
    }

    public String getProfessionalId() {
        return professionalId;
    }

    public String getServiceId() {
        return serviceId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public BookingState getState() {
        return state;
    }

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