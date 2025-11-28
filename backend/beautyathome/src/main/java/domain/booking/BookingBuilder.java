package domain.booking;

import java.time.LocalDateTime;
import java.util.UUID;

import domain.service.ServiceComponent;

public class BookingBuilder {

    private String id;
    private String clientId;
    private String professionalId;
    private String serviceId;
    private LocalDateTime dateTime;

    public BookingBuilder withId(String id) {
        this.id = id;
        return this;
    }

    public BookingBuilder withClient(String clientId) {
        this.clientId = clientId;
        return this;
    }

    public BookingBuilder withProfessional(String professionalId) {
        this.professionalId = professionalId;
        return this;
    }

    public BookingBuilder withService(String serviceId) {
        this.serviceId = serviceId;
        return this;
    }

    public BookingBuilder withService(ServiceComponent service) {
        if (service != null) {
            this.serviceId = service.getName();
        }
        return this;
    }

    public BookingBuilder withDate(LocalDateTime dateTime) {
        this.dateTime = dateTime;
        return this;
    }

    public Booking build() {
        String safeId = id == null ? UUID.randomUUID().toString() : id;
        return new Booking(safeId, clientId, professionalId, serviceId, dateTime);
    }
}
