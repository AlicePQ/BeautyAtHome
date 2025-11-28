package infrastructure.persistence.dao.memory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import domain.service.ServiceComponent;
import infrastructure.persistence.dao.ServiceDAO;

public class InMemoryServiceDAO implements ServiceDAO {

    private final Map<String, ServiceComponent> services = new ConcurrentHashMap<>();
    private final Map<String, List<ServiceComponent>> professionalServices = new ConcurrentHashMap<>();

    @Override
    public ServiceComponent save(ServiceComponent entity) {
        return saveForProfessional(null, entity);
    }

    @Override
    public ServiceComponent saveForProfessional(String professionalId, ServiceComponent serviceComponent) {
        if (serviceComponent == null) {
            throw new IllegalArgumentException("Service cannot be null");
        }
        String key = normalize(serviceComponent.getName());
        if (key.isEmpty()) {
            throw new IllegalArgumentException("Service must define a name");
        }
        services.put(key, serviceComponent);
        if (professionalId != null && !professionalId.isBlank()) {
            professionalServices.computeIfAbsent(professionalId, id -> new CopyOnWriteArrayList<>());
            List<ServiceComponent> components = professionalServices.get(professionalId);
            components.removeIf(existing -> normalize(existing.getName()).equals(key));
            components.add(serviceComponent);
        }
        return serviceComponent;
    }

    @Override
    public ServiceComponent findById(String id) {
        return services.get(normalize(id));
    }

    @Override
    public void delete(String id) {
        String key = normalize(id);
        ServiceComponent removed = services.remove(key);
        if (removed != null) {
            professionalServices.values().forEach(list ->
                    list.removeIf(component -> normalize(component.getName()).equals(key)));
        }
    }

    @Override
    public List<ServiceComponent> findByProfessionalId(String professionalId) {
        List<ServiceComponent> servicesForProfessional = professionalServices.get(professionalId);
        if (servicesForProfessional == null) {
            return Collections.emptyList();
        }
        return Collections.unmodifiableList(new ArrayList<>(servicesForProfessional));
    }

    private String normalize(String id) {
        return id == null ? "" : id.toLowerCase(Locale.ROOT);
    }
}
