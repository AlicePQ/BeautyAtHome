package application.booking.validation;

import application.booking.BookingRequest;
import infrastructure.persistence.dao.ClientDAO;

public class ConsentValidationHandler extends BookingRequestHandler {

    private final ClientDAO clientDAO;

    public ConsentValidationHandler(ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }

    @Override
    protected boolean doHandle(BookingRequest request) {
        return clientDAO.findById(request.getClientId()) != null;
    }
}
