package infrastructure.persistence.dao.memory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import domain.professional.Professional;
import infrastructure.persistence.dao.ProfessionalDAO;

public class InMemoryProfessionalDAO implements ProfessionalDAO {

    private final Map<String, Professional> store = new ConcurrentHashMap<>();

    @Override
    public Professional save(Professional entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Professional cannot be null");
        }
        if (entity.getId() == null || entity.getId().isBlank()) {
            throw new IllegalArgumentException("Professional id is required");
        }
        store.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public Professional findById(String id) {
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
    public List<Professional> findAll() {
        return Collections.unmodifiableList(new ArrayList<>(store.values()));
    }
}
