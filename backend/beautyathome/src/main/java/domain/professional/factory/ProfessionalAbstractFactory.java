package domain.professional.factory;

import java.util.Map;

import domain.professional.Professional;

public interface ProfessionalAbstractFactory {

	Professional createProfessional(String type, Map<String, Object> data);
}
