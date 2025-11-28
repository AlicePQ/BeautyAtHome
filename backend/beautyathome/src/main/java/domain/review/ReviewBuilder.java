package domain.review;

import java.time.LocalDateTime;

import domain.booking.Booking;
import domain.client.Client;
import domain.professional.Professional;
import domain.review.rating.RatingValueObject;

public class ReviewBuilder {

    private String id;
    private Booking booking;
    private Client client;
    private Professional professional;
    private RatingValueObject rating;
    private String text;
    private LocalDateTime createdAt;

    public ReviewBuilder withId(String id) {
        this.id = id;
        return this;
    }

    public ReviewBuilder withBooking(Booking booking) {
        this.booking = booking;
        return this;
    }

    public ReviewBuilder withClient(Client client) {
        this.client = client;
        return this;
    }

    public ReviewBuilder withProfessional(Professional professional) {
        this.professional = professional;
        return this;
    }

    public ReviewBuilder withRating(int ratingValue) {
        this.rating = new RatingValueObject(ratingValue);
        return this;
    }

    public ReviewBuilder withText(String text) {
        this.text = text;
        return this;
    }

    public ReviewBuilder withCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Review build() {
        return new Review(id, booking, client, professional, rating, text, createdAt);
    }
}
