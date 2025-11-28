package domain.booking.observer;


import domain.booking.Booking;
import domain.client.Client;

/**
 * Observer that represents the client as a notification target. It can later
 * be extended to send e-mails, push notifications, etc.
 */
public class ClientNotificationObserver implements NotificationObserver {

    private final Client client;

    /**
     * @param client client to notify when booking updates occur
     */
    public ClientNotificationObserver(Client client) {
        this.client = client;
    }

    @Override
    public void update(Booking booking) {
        // Aquí enviarías un email/push al cliente
        // usando client.getEmail() y el estado de booking.
    }
}
