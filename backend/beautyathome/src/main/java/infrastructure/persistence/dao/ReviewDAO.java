package infrastructure.persistence.dao;

import java.util.Collections;
import java.util.List;

import domain.review.Review;

public interface ReviewDAO extends BaseDAO<Review, String> {

	default List<Review> findByProfessionalId(String professionalId) {
		return Collections.emptyList();
	}
}
