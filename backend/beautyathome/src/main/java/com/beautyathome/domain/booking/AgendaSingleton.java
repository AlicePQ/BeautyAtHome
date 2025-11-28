package main.java.com.beautyathome.domain.booking;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AgendaSingleton {

    private static AgendaSingleton instance;

    private final List<Booking> bookings = new ArrayList<>();

    private AgendaSingleton() {}

    public static synchronized AgendaSingleton getInstance() {
        if (instance == null) {
            instance = new AgendaSingleton();
        }
        return instance;
    }

    public boolean availability(String professionalId, LocalDateTime dateTime, int durationMinutes) {
        // Lógica simple: verificar que no haya reservas con el mismo profesional y hora.
        return bookings.stream()
                .noneMatch(b -> b.getProfessionalId().equals(professionalId)
                        && b.getDateTime().equals(dateTime));
    }

    public Booking book(String bookingId,
                        String clientId,
                        String professionalId,
                        String serviceId,
                        LocalDateTime dateTime) {
        Booking booking = new Booking(bookingId, clientId, professionalId, serviceId, dateTime);
        bookings.add(booking);
        return booking;
    }

    // getters, cancel, reschedule pueden añadirse aquí.
}
