package main.java.com.beautyathome.application.facade;


import main.java.com.beautyathome.application.booking.BookingRequest;
import main.java.com.beautyathome.application.booking.BookingService;
import main.java.com.beautyathome.domain.booking.Booking;
import main.java.com.beautyathome.domain.client.Client;
import main.java.com.beautyathome.domain.pricing.PricingStrategy;
import main.java.com.beautyathome.domain.professional.Professional;
import main.java.com.beautyathome.domain.service.ServiceComponent;
import main.java.com.beautyathome.infrastructure.persistence.dao.*;

import java.time.LocalDateTime;
import java.util.List;

public class BeautyAtHomeFacade {

    private final ClientDAO clientDAO;
    private final ProfessionalDAO professionalDAO;
    private final ServiceDAO serviceDAO;
    private final BookingDAO bookingDAO;
    private final ReviewDAO reviewDAO;
    private final BookingService bookingService;
    private final PricingStrategy pricingStrategy;

    public BeautyAtHomeFacade(ClientDAO clientDAO,
                              ProfessionalDAO professionalDAO,
                              ServiceDAO serviceDAO,
                              BookingDAO bookingDAO,
                              ReviewDAO reviewDAO,
                              BookingService bookingService,
                              PricingStrategy pricingStrategy) {
        this.clientDAO = clientDAO;
        this.professionalDAO = professionalDAO;
        this.serviceDAO = serviceDAO;
        this.bookingDAO = bookingDAO;
        this.reviewDAO = reviewDAO;
        this.bookingService = bookingService;
        this.pricingStrategy = pricingStrategy;
    }

    public Client registerClient(Client client) {
        return clientDAO.save(client);
    }

    public Professional registerProfessional(Professional professional) {
        return professionalDAO.save(professional);
    }

    public List<ServiceComponent> listServicesByProfessional(String professionalId) {
        // ejemplo simple, podrías delegar al DAO
        return null;
    }

    public Booking bookService(String clientId,
                               String professionalId,
                               String serviceId,
                               LocalDateTime dateTime) {

        BookingRequest request = new BookingRequest();
        request.setClientId(clientId);
        request.setProfessionalId(professionalId);
        request.setServiceId(serviceId);
        request.setDateTime(dateTime);

        Booking booking = bookingService.book(request);
        return bookingDAO.save(booking);
    }
}
