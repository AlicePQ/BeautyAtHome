package application.booking.validation;

import application.booking.BookingRequest;
import domain.booking.AgendaSingleton;

public class AvailabilityValidationHandler extends BookingRequestHandler {

    private final AgendaSingleton agendaSingleton;
    private final int defaultDurationMinutes;

    public AvailabilityValidationHandler(AgendaSingleton agendaSingleton, int defaultDurationMinutes) {
        this.agendaSingleton = agendaSingleton;
        this.defaultDurationMinutes = defaultDurationMinutes;
    }

    @Override
    protected boolean doHandle(BookingRequest request) {
        if (request.getDateTime() == null) {
            return false;
        }
        return agendaSingleton.availability(
                request.getProfessionalId(),
                request.getDateTime(),
                defaultDurationMinutes
        );
    }
}