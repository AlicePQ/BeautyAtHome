package infrastructure.proxy;

import domain.professional.CoverageArea;
import domain.professional.Professional;

public class CoverageProxy {

	private final Professional professional;

	public CoverageProxy(Professional professional) {
		this.professional = professional;
	}

	public boolean isAvailable(String zone) {
		if (zone == null || zone.isBlank()) {
			return false;
		}
		if (professional.getCoverageAreas() == null) {
			return false;
		}
		for (CoverageArea area : professional.getCoverageAreas()) {
			if (area.getName().equalsIgnoreCase(zone)) {
				return true;
			}
		}
		return false;
	}
}
