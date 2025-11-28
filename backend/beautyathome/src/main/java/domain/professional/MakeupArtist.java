package domain.professional;

import java.util.List;

import domain.service.ServiceComponent;

public class MakeupArtist implements Professional {

	private final String id;
	private final String name;
	private final String photoUrl;
	private final String experienceSummary;
	private final List<CoverageArea> coverageAreas;
	private final Brand brand;
	private final List<ServiceComponent> servicesOffered;

	public MakeupArtist(String id,
						String name,
						String photoUrl,
						String experienceSummary,
						List<CoverageArea> coverageAreas,
						Brand brand,
						List<ServiceComponent> servicesOffered) {
		this.id = id;
		this.name = name;
		this.photoUrl = photoUrl;
		this.experienceSummary = experienceSummary;
		this.coverageAreas = coverageAreas;
		this.brand = brand;
		this.servicesOffered = servicesOffered;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getPhotoUrl() {
		return photoUrl;
	}

	@Override
	public String getExperienceSummary() {
		return experienceSummary;
	}

	@Override
	public List<CoverageArea> getCoverageAreas() {
		return coverageAreas;
	}

	@Override
	public Brand getBrand() {
		return brand;
	}

	@Override
	public List<ServiceComponent> getServicesOffered() {
		return servicesOffered;
	}
}
