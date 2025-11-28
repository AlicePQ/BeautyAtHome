package application.booking;

import application.booking.validation.BookingRequestHandler;
import domain.booking.AgendaSingleton;
import domain.booking.Booking;
import domain.booking.BookingBuilder;

public class BookingService {

    private final BookingRequestHandler validationChain;
    private final AgendaSingleton agenda;

    public BookingService(BookingRequestHandler validationChain, AgendaSingleton agenda) {
        this.validationChain = validationChain;
        this.agenda = agenda;
    }

    public Booking book(BookingRequest request) {
        if (!validationChain.handle(request)) {
            throw new IllegalStateException("Booking validation failed");
        }

        Booking draft = new BookingBuilder()
            .withClient(request.getClientId())
            .withProfessional(request.getProfessionalId())
            .withService(request.getServiceId())
            .withDate(request.getDateTime())
            .build();

        return agenda.book(
            draft.getId(),
            draft.getClientId(),
            draft.getProfessionalId(),
            draft.getServiceId(),
            draft.getDateTime()
        );
    }
}

