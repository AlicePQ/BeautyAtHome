package domain.booking.history;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import domain.booking.Booking;
import domain.client.Client;
import domain.professional.Professional;
import domain.service.ServiceComponent;
import domain.service.image.Photo;

public class ServiceHistory {

	private final Booking booking;
	private final Client client;
	private final Professional professional;
	private final ServiceComponent service;
	private final LocalDateTime dateTime;
	private final List<Photo> photos = new ArrayList<>();

	public ServiceHistory(Booking booking,
						  Client client,
						  Professional professional,
						  ServiceComponent service,
						  LocalDateTime dateTime) {
		this.booking = booking;
		this.client = client;
		this.professional = professional;
		this.service = service;
		this.dateTime = dateTime;
	}

	public Booking getBooking() {
		return booking;
	}

	public Client getClient() {
		return client;
	}

	public Professional getProfessional() {
		return professional;
	}

	public ServiceComponent getService() {
		return service;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void addPhoto(Photo photo) {
		photos.add(photo);
	}

	public List<Photo> getPhotos() {
		return Collections.unmodifiableList(photos);
	}
}
