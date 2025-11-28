package main.java.com.beautyathome.application.booking;

import java.time.LocalDateTime;

public class BookingRequest {

    private String clientId;
    private String professionalId;
    private String serviceId;
    private String zone;
    private LocalDateTime dateTime;

    public BookingRequest(String clientId, String professionalId, String serviceId, String zone, LocalDateTime dateTime) {
        this.clientId = clientId;
        this.professionalId = professionalId;
        this.serviceId = serviceId;
        this.zone = zone;
        this.dateTime = dateTime;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getProfessionalId() {
        return professionalId;
    }

    public void setProfessionalId(String professionalId) {
        this.professionalId = professionalId;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}