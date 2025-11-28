package domain.booking;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import domain.professional.Professional;
import domain.service.ServiceComponent;

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

    public synchronized boolean availability(String professionalId, LocalDateTime dateTime, int durationMinutes) {
        return bookings.stream().noneMatch(booking ->
                booking.getProfessionalId().equals(professionalId)
                        && overlaps(booking.getDateTime(), dateTime, durationMinutes));
    }

    public boolean availability(Professional professional, LocalDateTime dateTime, int durationMinutes) {
        return availability(professional.getId(), dateTime, durationMinutes);
    }

    public synchronized Booking book(String bookingId,
                                     String clientId,
                                     String professionalId,
                                     String serviceId,
                                     LocalDateTime dateTime) {
        Booking booking = new Booking(bookingId, clientId, professionalId, serviceId, dateTime);
        bookings.add(booking);
        return booking;
    }

    public Booking book(String clientId,
                        String professionalId,
                        ServiceComponent service,
                        LocalDateTime dateTime) {
        String bookingId = UUID.randomUUID().toString();
        String serviceId = service == null ? "" : service.getName();
        return book(bookingId, clientId, professionalId, serviceId, dateTime);
    }

    public synchronized boolean cancel(String bookingId) {
        Iterator<Booking> iterator = bookings.iterator();
        while (iterator.hasNext()) {
            Booking booking = iterator.next();
            if (booking.getId().equals(bookingId)) {
                iterator.remove();
                booking.cancel();
                return true;
            }
        }
        return false;
    }

    public synchronized boolean reschedule(String bookingId, LocalDateTime newDate) {
        for (Booking booking : bookings) {
            if (booking.getId().equals(bookingId)) {
                booking.setDateTime(newDate);
                booking.notifyObservers();
                return true;
            }
        }
        return false;
    }

    public List<Booking> getBookings() {
        return new ArrayList<>(bookings);
    }

    private boolean overlaps(LocalDateTime existing, LocalDateTime requested, int durationMinutes) {
        if (existing == null || requested == null) {
            return false;
        }
        LocalDateTime endExisting = existing.plusMinutes(durationMinutes);
        LocalDateTime endRequested = requested.plusMinutes(durationMinutes);
        return !requested.isAfter(endExisting) && !existing.isAfter(endRequested);
    }
}
