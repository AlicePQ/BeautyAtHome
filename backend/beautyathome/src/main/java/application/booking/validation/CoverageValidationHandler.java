package application.booking.validation;


import application.booking.BookingRequest;
import domain.professional.Professional;
import infrastructure.persistence.dao.ProfessionalDAO;
import infrastructure.proxy.CoverageProxy;

public class CoverageValidationHandler extends BookingRequestHandler {

    private final ProfessionalDAO professionalDAO;

    public CoverageValidationHandler(ProfessionalDAO professionalDAO) {
        this.professionalDAO = professionalDAO;
    }

    @Override
    protected boolean doHandle(BookingRequest request) {
        Professional professional = professionalDAO.findById(request.getProfessionalId());
        if (professional == null) {
            return false;
        }
        if (request.getZone() == null || request.getZone().isBlank()) {
            return true;
        }
        CoverageProxy proxy = new CoverageProxy(professional);
        return proxy.isAvailable(request.getZone());
    }
}
