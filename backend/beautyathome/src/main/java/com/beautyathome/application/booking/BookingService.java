package main.java.com.beautyathome.application.booking;

import main.java.com.beautyathome.application.booking.validation.BookingRequestHandler;
import main.java.com.beautyathome.domain.booking.AgendaSingleton;
import main.java.com.beautyathome.domain.booking.Booking;

import java.time.LocalDateTime;
import java.util.UUID;

public class BookingService {

    private final BookingRequestHandler validationChain;
    private final AgendaSingleton agenda = AgendaSingleton.getInstance();

    public BookingService(BookingRequestHandler validationChain) {
        this.validationChain = validationChain;
    }

    public Booking book(BookingRequest request) {
        if (!validationChain.handle(request)) {
            throw new IllegalStateException("Booking validation failed");
        }

        String bookingId = UUID.randomUUID().toString();
        return agenda.book(
                bookingId,
                request.getClientId(),
                request.getProfessionalId(),
                request.getServiceId(),
                request.getDateTime()
        );
    }
}

