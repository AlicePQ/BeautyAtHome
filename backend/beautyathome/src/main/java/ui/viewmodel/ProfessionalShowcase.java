package ui.viewmodel;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import domain.booking.history.ServiceHistory;
import domain.professional.Professional;
import domain.review.Review;
import domain.service.ServiceComponent;

/**
 * Aggregates everything needed to render a single professional in the deluxe showcase.
 */
public class ProfessionalShowcase {

    private final Professional professional;
    private final List<ServiceComponent> services;
    private final List<ServiceHistory> histories;
    private final List<Review> reviews;
    private final double averageRating;

    public ProfessionalShowcase(Professional professional,
                                List<ServiceComponent> services,
                                List<ServiceHistory> histories,
                                List<Review> reviews,
                                double averageRating) {
        this.professional = professional;
        this.services = services == null ? Collections.emptyList() : List.copyOf(services);
        this.histories = histories == null ? Collections.emptyList() : List.copyOf(histories);
        this.reviews = reviews == null ? Collections.emptyList() : List.copyOf(reviews);
        this.averageRating = averageRating;
    }

    public Professional getProfessional() {
        return professional;
    }

    public List<ServiceComponent> getServices() {
        return services;
    }

    public List<ServiceHistory> getHistories() {
        return histories;
    }

    public ServiceHistory getHeroHistory() {
        return histories.isEmpty() ? null : histories.get(0);
    }

    public List<ServiceHistory> getHighlightHistories() {
        return histories.stream().limit(3).collect(Collectors.toList());
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public List<Review> getFeaturedReviews() {
        return reviews.stream().limit(3).collect(Collectors.toList());
    }

    public double getAverageRating() {
        return averageRating;
    }
}
