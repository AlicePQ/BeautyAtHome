package config;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import application.facade.BeautyAtHomeFacade;
import domain.booking.Booking;
import domain.client.Client;
import domain.professional.Professional;
import domain.service.ServiceComponent;
import infrastructure.persistence.dao.ClientDAO;
import infrastructure.persistence.dao.ProfessionalDAO;
import infrastructure.persistence.dao.ServiceDAO;

/**
 * Seeds the in-memory DAOs with sample data so the MVC views display meaningful
 * information on startup. It creates five clients, five professionals, their
 * services, five bookings, sample reviews, and photo consent to exercise the
 * full application workflow.
 */
@Component
public class SampleDataInitializer implements CommandLineRunner {

    private final BeautyAtHomeFacade facade;
    private final ClientDAO clientDAO;
    private final ProfessionalDAO professionalDAO;
    private final ServiceDAO serviceDAO;

    public SampleDataInitializer(BeautyAtHomeFacade facade,
                                 ClientDAO clientDAO,
                                 ProfessionalDAO professionalDAO,
                                 ServiceDAO serviceDAO) {
        this.facade = facade;
        this.clientDAO = clientDAO;
        this.professionalDAO = professionalDAO;
        this.serviceDAO = serviceDAO;
    }

    @Override
    public void run(String... args) {
        if (!clientDAO.findAll().isEmpty() || !professionalDAO.findAll().isEmpty()) {
            return; // assume data already seeded
        }
        List<Client> clients = seedClients();
        List<Professional> professionals = seedProfessionals();
        seedServices(professionals);
        List<Booking> bookings = seedBookings(clients, professionals);
        seedReviewsAndPhotos(bookings);
    }

    private List<Client> seedClients() {
        return List.of(
                facade.registerClient(Map.of("id", "cli-001", "name", "Mariana Torres", "email", "mariana@mail.com")),
                facade.registerClient(Map.of("id", "cli-002", "name", "Carlos Gomez", "email", "carlos@mail.com")),
                facade.registerClient(Map.of("id", "cli-003", "name", "Luisa Fernanda", "email", "luisa@mail.com")),
                facade.registerClient(Map.of("id", "cli-004", "name", "Andrea Salas", "email", "andrea@mail.com")),
                facade.registerClient(Map.of("id", "cli-005", "name", "Julian Reyes", "email", "julian@mail.com"))
        );
    }

    private List<Professional> seedProfessionals() {
        return List.of(
                registerProfessional("pro-001", "hairstylist", "Diana Styling", "Chapinero, Usaquén", "LuxHair"),
                registerProfessional("pro-002", "makeupartist", "Aura Makeup", "Suba, Engativá", "GlamPro"),
                registerProfessional("pro-003", "manicurist", "Nails by Sofi", "Chapinero, Teusaquillo", "NailArt"),
                registerProfessional("pro-004", "hairstylist", "Barber Max", "Kennedy, Bosa", "UrbanCut"),
                registerProfessional("pro-005", "makeupartist", "Luna Glam", "Fontibón, Engativá", "BellezaTotal")
        );
    }

    private Professional registerProfessional(String id,
                                              String type,
                                              String name,
                                              String coverage,
                                              String brandName) {
        Map<String, Object> data = new HashMap<>();
        data.put("id", id);
        data.put("type", type);
        data.put("name", name);
        data.put("photoUrl", "https://picsum.photos/seed/" + id + "/300/300");
        data.put("experienceSummary", "Experiencia certificada en servicios premium");
        data.put("coverage", List.of(coverage.split(",\s*")));
        Map<String, String> brand = new HashMap<>();
        brand.put("name", brandName);
        brand.put("logo", "https://picsum.photos/seed/" + brandName + "/100/100");
        data.put("brand", brand);
        data.put("services", List.of());
        return facade.registerProfessional(data);
    }

    private void seedServices(List<Professional> professionals) {
        for (Professional professional : professionals) {
            facade.createBasicService(
                    professional.getId(),
                    professional.getName() + " Servicio Estrella",
                    "Servicio personalizado con productos premium",
                    120_000,
                    90,
                    List.of("https://picsum.photos/seed/" + professional.getId() + "svc/640/480")
            );
        }
    }

    private List<Booking> seedBookings(List<Client> clients, List<Professional> professionals) {
        LocalDateTime baseDate = LocalDateTime.now().plusDays(1).withHour(10);
        return List.of(
                createBooking(clients.get(0), professionals.get(0), baseDate),
                createBooking(clients.get(1), professionals.get(1), baseDate.plusHours(2)),
                createBooking(clients.get(2), professionals.get(2), baseDate.plusDays(1)),
                createBooking(clients.get(3), professionals.get(3), baseDate.plusDays(1).plusHours(3)),
                createBooking(clients.get(4), professionals.get(4), baseDate.plusDays(2))
        );
    }

    private Booking createBooking(Client client, Professional professional, LocalDateTime dateTime) {
        ServiceComponent service = serviceDAO.findByProfessionalId(professional.getId()).stream()
                .findFirst()
                .orElseThrow();
        String zone = professional.getCoverageAreas() != null && !professional.getCoverageAreas().isEmpty()
            ? professional.getCoverageAreas().get(0).getName()
            : "Chapinero";
        return facade.bookService(client.getId(), professional.getId(), service.getName(), dateTime, zone);
    }

    private void seedReviewsAndPhotos(List<Booking> bookings) {
        int[] ratings = new int[]{5, 4, 5, 3, 4};
        for (int i = 0; i < bookings.size(); i++) {
            Booking booking = bookings.get(i);
            facade.grantPhotoConsent(booking.getId());
            facade.uploadPhoto(booking.getId(), "https://picsum.photos/seed/photo" + booking.getId() + "/800/600", true);
            facade.addReview(booking.getId(), ratings[i % ratings.length], "Excelente servicio y atención");
        }
    }
}
