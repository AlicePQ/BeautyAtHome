package application.facade;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

import application.booking.BookingRequest;
import application.booking.BookingService;
import domain.booking.Booking;
import domain.booking.history.ServiceHistory;
import domain.booking.observer.ClientNotificationObserver;
import domain.booking.observer.ProfessionalNotificationObserver;
import domain.client.Client;
import domain.pricing.PricingStrategy;
import domain.professional.Professional;
import domain.professional.factory.ProfessionalAbstractFactory;
import domain.review.Review;
import domain.service.ServiceComponent;
import domain.service.ServiceLeaf;
import domain.service.builder.ServiceDirector;
import domain.service.image.Photo;
import infrastructure.media.ConsentProxy;
import infrastructure.persistence.dao.BookingDAO;
import infrastructure.persistence.dao.ClientDAO;
import infrastructure.persistence.dao.ProfessionalDAO;
import infrastructure.persistence.dao.ReviewDAO;
import infrastructure.persistence.dao.ServiceDAO;
import infrastructure.proxy.CoverageProxy;
import infrastructure.proxy.ReviewGuardProxy;

public class BeautyAtHomeFacade {

    private final ClientDAO clientDAO;
    private final ProfessionalDAO professionalDAO;
    private final ServiceDAO serviceDAO;
    private final BookingDAO bookingDAO;
    private final ReviewDAO reviewDAO;
    private final BookingService bookingService;
    private final PricingStrategy pricingStrategy;
    private final ProfessionalAbstractFactory professionalFactory;
    private final ServiceDirector serviceDirector;
    private final ReviewGuardProxy reviewGuardProxy;
    private final ConsentProxy consentProxy;

    public BeautyAtHomeFacade(ClientDAO clientDAO,
                              ProfessionalDAO professionalDAO,
                              ServiceDAO serviceDAO,
                              BookingDAO bookingDAO,
                              ReviewDAO reviewDAO,
                              BookingService bookingService,
                              PricingStrategy pricingStrategy,
                              ProfessionalAbstractFactory professionalFactory,
                              ServiceDirector serviceDirector,
                              ReviewGuardProxy reviewGuardProxy,
                              ConsentProxy consentProxy) {
        this.clientDAO = clientDAO;
        this.professionalDAO = professionalDAO;
        this.serviceDAO = serviceDAO;
        this.bookingDAO = bookingDAO;
        this.reviewDAO = reviewDAO;
        this.bookingService = bookingService;
        this.pricingStrategy = pricingStrategy;
        this.professionalFactory = professionalFactory;
        this.serviceDirector = serviceDirector;
        this.reviewGuardProxy = reviewGuardProxy;
        this.consentProxy = consentProxy;
    }

    public Client registerClient(Map<String, Object> data) {
        Client client = new Client(
                (String) data.get("id"),
                (String) data.getOrDefault("name", "Anonymous"),
                (String) data.getOrDefault("email", "")
        );
        return registerClient(client);
    }

    public Client registerClient(Client client) {
        Objects.requireNonNull(client, "client");
        Client normalized = client.getId() == null || client.getId().isBlank()
                ? new Client(UUID.randomUUID().toString(), client.getName(), client.getEmail())
                : client;
        return clientDAO.save(normalized);
    }

    public Professional registerProfessional(Map<String, Object> data) {
        String type = (String) data.get("type");
        Objects.requireNonNull(type, "type");
        Professional professional = professionalFactory.createProfessional(type, data);
        return professionalDAO.save(professional);
    }

    public Professional registerProfessional(Professional professional) {
        Objects.requireNonNull(professional, "professional");
        return professionalDAO.save(professional);
    }

    public List<Professional> searchProfessionals(String zone, String category) {
        return professionalDAO.findAll().stream()
                .filter(pro -> matchesZone(pro, zone))
                .filter(pro -> matchesCategory(pro, category))
                .collect(Collectors.toList());
    }

    public List<ServiceComponent> listServices(String professionalId) {
        return new ArrayList<>(serviceDAO.findByProfessionalId(professionalId));
    }

    public ServiceComponent createBasicService(String professionalId,
                                               String name,
                                               String description,
                                               double price,
                                               int duration,
                                               List<String> imageUrls) {
        if (professionalDAO.findById(professionalId) == null) {
            throw new IllegalArgumentException("Professional not found: " + professionalId);
        }
        ServiceComponent service = serviceDirector.constructService(name, description, price, duration, imageUrls);
        return serviceDAO.saveForProfessional(professionalId, service);
    }

    public Booking bookService(String clientId,
                               String professionalId,
                               String serviceId,
                               LocalDateTime dateTime) {
        return bookService(clientId, professionalId, serviceId, dateTime, null);
    }

    public Booking bookService(String clientId,
                               String professionalId,
                               String serviceId,
                               LocalDateTime dateTime,
                               String zone) {
        ServiceComponent service = serviceDAO.findById(serviceId);
        Client client = clientDAO.findById(clientId);
        Professional professional = professionalDAO.findById(professionalId);

        if (service == null) {
            throw new IllegalArgumentException("Service not found: " + serviceId);
        }
        if (client == null) {
            throw new IllegalArgumentException("Client not found: " + clientId);
        }
        if (professional == null) {
            throw new IllegalArgumentException("Professional not found: " + professionalId);
        }

        pricingStrategy.calculatePrice(service.getPrice(), client, service);

        BookingRequest request = new BookingRequest();
        request.setClientId(clientId);
        request.setProfessionalId(professionalId);
        request.setServiceId(serviceId);
        request.setDateTime(dateTime);
        request.setZone(zone);

        Booking booking = bookingService.book(request);
        booking.attach(new ClientNotificationObserver(client));
        booking.attach(new ProfessionalNotificationObserver(professional));
        Booking persisted = bookingDAO.save(booking);
        // El cálculo de precios se mantiene para integraciones futuras (facturación, etc.)
        return persisted;
    }

    public Review addReview(String bookingId, int rating, String text) {
        return reviewGuardProxy.createReview(bookingId, rating, text);
    }

    public void uploadPhoto(String bookingId, String url, boolean isPublic) {
        consentProxy.addPhoto(bookingId, url, isPublic);
    }

    public void grantPhotoConsent(String bookingId) {
        consentProxy.addConsent(bookingId);
    }

    public double getProfessionalAverageRating(String professionalId) {
        return reviewDAO.findByProfessionalId(professionalId).stream()
                .mapToInt(review -> review.getRating().getValue())
                .average()
                .orElse(0.0);
    }

    public List<ServiceHistory> viewProfessionalHistory(String professionalId) {
        List<Booking> bookings = bookingDAO.findByProfessionalId(professionalId);
        Professional professional = professionalDAO.findById(professionalId);
        if (professional == null) {
            return Collections.emptyList();
        }
        List<Photo> professionalPhotos = consentProxy.listByProfessional(professionalId);
        List<ServiceHistory> histories = new ArrayList<>();
        for (Booking booking : bookings) {
            Client client = clientDAO.findById(booking.getClientId());
            if (client == null) {
                continue;
            }
            ServiceComponent service = serviceDAO.findById(booking.getServiceId());
            ServiceHistory history = new ServiceHistory(booking, client, professional, service, booking.getDateTime());
            List<Photo> photos = professionalPhotos.stream()
                    .filter(photo -> Objects.equals(photo.getBookingId(), booking.getId()))
                    .collect(Collectors.toList());
            photos.forEach(history::addPhoto);
            histories.add(history);
        }
        return histories;
    }

    private boolean matchesCategory(Professional professional, String category) {
        if (category == null || category.isBlank()) {
            return true;
        }
        if (professional.getServicesOffered() == null) {
            return false;
        }
        return professional.getServicesOffered().stream().anyMatch(service -> {
            if (service instanceof ServiceLeaf leaf && leaf.getCategory() != null) {
                return leaf.getCategory().getName().equalsIgnoreCase(category);
            }
            return service.getName().equalsIgnoreCase(category);
        });
    }

    private boolean matchesZone(Professional professional, String zone) {
        if (zone == null || zone.isBlank()) {
            return true;
        }
        CoverageProxy proxy = new CoverageProxy(professional);
        return proxy.isAvailable(zone);
    }
}
