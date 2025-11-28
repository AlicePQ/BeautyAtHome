package infrastructure.persistence.dao;

import java.util.Collections;
import java.util.List;

import domain.review.Review;

/**
 * DAO especializado para reseñas de profesionales.
 */
public interface ReviewDAO extends BaseDAO<Review, String> {

	/**
	 * Busca reseñas asociadas a una profesional.
	 *
	 * @param professionalId profesional evaluada
	 * @return lista de reseñas encontradas
	 */
	default List<Review> findByProfessionalId(String professionalId) {
		return Collections.emptyList();
	}
}
