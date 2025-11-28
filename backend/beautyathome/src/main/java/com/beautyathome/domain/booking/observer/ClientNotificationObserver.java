package main.java.com.beautyathome.domain.booking.observer;


import main.java.com.beautyathome.domain.booking.Booking;
import main.java.com.beautyathome.domain.client.Client;

public class ClientNotificationObserver implements NotificationObserver {

    private final Client client;

    public ClientNotificationObserver(Client client) {
        this.client = client;
    }

    @Override
    public void update(Booking booking) {
        // Aquí enviarías un email/push al cliente
        // usando client.getEmail() y el estado de booking.
    }
}
