package application.booking.validation;

import application.booking.BookingRequest;
import infrastructure.persistence.dao.ClientDAO;

public class PaymentValidationHandler extends BookingRequestHandler {

    private final ClientDAO clientDAO;

    public PaymentValidationHandler(ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }

    @Override
    protected boolean doHandle(BookingRequest request) {
        return clientDAO.findById(request.getClientId()) != null
                && request.getServiceId() != null;
    }
}
