package infrastructure.persistence.dao.memory;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import domain.client.Client;
import infrastructure.persistence.dao.ClientDAO;

public class InMemoryClientDAO implements ClientDAO {

    private final Map<String, Client> store = new ConcurrentHashMap<>();

    @Override
    public Client save(Client entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Client cannot be null");
        }
        Client normalized = entity.getId() == null || entity.getId().isBlank()
                ? new Client(UUID.randomUUID().toString(), entity.getName(), entity.getEmail())
                : entity;
        store.put(normalized.getId(), normalized);
        return normalized;
    }

    @Override
    public Client findById(String id) {
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
}
