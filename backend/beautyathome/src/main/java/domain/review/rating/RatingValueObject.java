package domain.review.rating;

public class RatingValueObject {

	private final int value;

	public RatingValueObject(int value) {
		if (value < 1 || value > 5) {
			throw new IllegalArgumentException("Rating must be between 1 and 5");
		}
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
