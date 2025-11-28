package infrastructure.persistence.dao.memory;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import domain.review.Review;
import infrastructure.persistence.dao.ReviewDAO;

public class InMemoryReviewDAO implements ReviewDAO {

    private final Map<String, Review> store = new ConcurrentHashMap<>();

    @Override
    public Review save(Review entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Review cannot be null");
        }
        store.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public Review findById(String id) {
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
    public List<Review> findByProfessionalId(String professionalId) {
        if (professionalId == null) {
            return Collections.emptyList();
        }
        return store.values().stream()
                .filter(review -> review.getProfessional() != null)
                .filter(review -> professionalId.equals(review.getProfessional().getId()))
                .collect(Collectors.toList());
    }
}
