package main.java.com.beautyathome.application.booking.validation;

import main.java.com.beautyathome.application.booking.BookingRequest;

public class PaymentValidationHandler extends BookingRequestHandler {

    @Override
    protected boolean doHandle(BookingRequest request) {
        // Aquí verificarías si el profesional cubre la zona request.getZone()
        // Por ahora devolvemos true como ejemplo.
        return true;
    }
}
