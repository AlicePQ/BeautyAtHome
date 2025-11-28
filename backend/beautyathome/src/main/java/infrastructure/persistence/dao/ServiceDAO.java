package infrastructure.persistence.dao;

import java.util.Collections;
import java.util.List;

import domain.service.ServiceComponent;

public interface ServiceDAO extends BaseDAO<ServiceComponent, String> {

	default List<ServiceComponent> findByProfessionalId(String professionalId) {
		return Collections.emptyList();
	}

	default ServiceComponent saveForProfessional(String professionalId, ServiceComponent serviceComponent) {
		return save(serviceComponent);
	}
}
