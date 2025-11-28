package domain.review;

import java.time.LocalDateTime;
import java.util.UUID;

import domain.booking.Booking;
import domain.client.Client;
import domain.professional.Professional;
import domain.review.rating.RatingValueObject;

public class Review {

	private final String id;
	private final Booking booking;
	private final Client client;
	private final Professional professional;
	private final RatingValueObject rating;
	private final String text;
	private final LocalDateTime createdAt;

	public Review(String id,
				  Booking booking,
				  Client client,
				  Professional professional,
				  RatingValueObject rating,
				  String text,
				  LocalDateTime createdAt) {
		this.id = id == null ? UUID.randomUUID().toString() : id;
		this.booking = booking;
		this.client = client;
		this.professional = professional;
		this.rating = rating;
		this.text = text;
		this.createdAt = createdAt == null ? LocalDateTime.now() : createdAt;
	}

	public String getId() {
		return id;
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

	public RatingValueObject getRating() {
		return rating;
	}

	public String getText() {
		return text;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
}
