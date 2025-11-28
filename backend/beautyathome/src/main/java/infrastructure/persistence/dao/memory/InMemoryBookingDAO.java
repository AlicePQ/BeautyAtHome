package infrastructure.persistence.dao.memory;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import domain.booking.Booking;
import domain.booking.BookingBuilder;
import infrastructure.persistence.dao.BookingDAO;

public class InMemoryBookingDAO implements BookingDAO {

    private final Map<String, Booking> store = new ConcurrentHashMap<>();

    @Override
    public Booking save(Booking entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Booking cannot be null");
        }
        Booking ensured = entity.getId() == null || entity.getId().isBlank()
                ? new BookingBuilder()
                    .withClient(entity.getClientId())
                    .withProfessional(entity.getProfessionalId())
                    .withService(entity.getServiceId())
                    .withDate(entity.getDateTime())
                    .build()
                : entity;
        store.put(ensured.getId(), ensured);
        return ensured;
    }

    @Override
    public Booking findById(String id) {
        if (id == null) {
            return null;
        }
        return store.get(id);
    }

    @Override
    public void delete(String id) {
        if (id != null) {
            store.remove(id);
        }
    }

    @Override
    public List<Booking> findByProfessionalId(String professionalId) {
        if (professionalId == null) {
            return Collections.emptyList();
        }
        return store.values().stream()
                .filter(booking -> professionalId.equals(booking.getProfessionalId()))
                .sorted(Comparator.comparing(Booking::getDateTime, Comparator.nullsLast(Comparator.naturalOrder())))
                .collect(Collectors.toList());
    }
}
