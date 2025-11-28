package infrastructure.persistence.dao;

import java.util.Collections;
import java.util.List;

import domain.booking.Booking;

public interface BookingDAO extends BaseDAO<Booking, String> {

	default List<Booking> findByProfessionalId(String professionalId) {
		return Collections.emptyList();
	}
}
