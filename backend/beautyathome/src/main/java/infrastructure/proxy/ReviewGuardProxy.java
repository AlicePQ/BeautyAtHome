package infrastructure.proxy;

import java.util.HashSet;
import java.util.Set;

import domain.review.Review;

public class ReviewGuardProxy {

	private final ReviewService real;
	private final Set<String> reviewedBookings = new HashSet<>();

	public ReviewGuardProxy(ReviewService real) {
		this.real = real;
	}

	public synchronized Review createReview(String bookingId, int rating, String text) {
		if (reviewedBookings.contains(bookingId)) {
			throw new IllegalStateException("Booking already reviewed");
		}
		if (rating < 1 || rating > 5) {
			throw new IllegalArgumentException("Invalid rating");
		}
		Review review = real.createReview(bookingId, rating, text);
		reviewedBookings.add(bookingId);
		return review;
	}
}
