package infrastructure.persistence.dao;

import java.util.Collections;
import java.util.List;

import domain.professional.Professional;

public interface ProfessionalDAO extends BaseDAO<Professional, String> {

	default List<Professional> findAll() {
		return Collections.emptyList();
	}
}
