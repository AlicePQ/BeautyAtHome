package application.review;

import domain.review.Review;
import infrastructure.proxy.ReviewGuardProxy;

public class ReviewServiceApplication {

	private final ReviewGuardProxy reviewGuardProxy;

	public ReviewServiceApplication(ReviewGuardProxy reviewGuardProxy) {
		this.reviewGuardProxy = reviewGuardProxy;
	}

	public Review addReview(String bookingId, int rating, String text) {
		return reviewGuardProxy.createReview(bookingId, rating, text);
	}
}
