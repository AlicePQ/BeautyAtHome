package domain.booking.observer;


import domain.booking.Booking;
import domain.client.Client;

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
