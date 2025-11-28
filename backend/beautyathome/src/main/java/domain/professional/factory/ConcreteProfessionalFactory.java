package domain.professional.factory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import domain.professional.Brand;
import domain.professional.CoverageArea;
import domain.professional.HairStylist;
import domain.professional.MakeupArtist;
import domain.professional.Manicurist;
import domain.professional.Professional;
import domain.service.ServiceComponent;

public class ConcreteProfessionalFactory implements ProfessionalAbstractFactory {

	@Override
	public Professional createProfessional(String type, Map<String, Object> data) {
		String normalized = type == null ? "" : type.toLowerCase();
		return switch (normalized) {
			case "hairstylist" -> buildProfessional(new HairStylistBuilder(), data);
			case "makeupartist" -> buildProfessional(new MakeupArtistBuilder(), data);
			case "manicurist" -> buildProfessional(new ManicuristBuilder(), data);
			default -> throw new IllegalArgumentException("Unsupported professional type: " + type);
		};
	}

	@SuppressWarnings("unchecked")
	private Professional buildProfessional(ProfessionalBuilder builder, Map<String, Object> data) {
		String id = (String) data.getOrDefault("id", UUID.randomUUID().toString());
		String name = (String) data.getOrDefault("name", "Unnamed");
		String photoUrl = (String) data.getOrDefault("photoUrl", "");
		String experience = (String) data.getOrDefault("experienceSummary", "");
		List<String> coverageNames = (List<String>) data.getOrDefault("coverage", Collections.emptyList());
		List<ServiceComponent> services = (List<ServiceComponent>) data.getOrDefault("services", Collections.emptyList());
		Map<String, String> brandData = (Map<String, String>) data.get("brand");
		Brand brand = brandData == null ? null : new Brand(
				brandData.getOrDefault("name", ""),
				brandData.getOrDefault("logo", "")
		);

		builder.setId(id)
				.setName(name)
				.setPhotoUrl(photoUrl)
				.setExperienceSummary(experience)
				.setCoverageAreas(buildCoverageAreas(coverageNames))
				.setBrand(brand)
				.setServices(services);
		return builder.build();
	}

	private List<CoverageArea> buildCoverageAreas(List<String> names) {
		List<CoverageArea> coverageAreas = new ArrayList<>();
		if (names == null) {
			return coverageAreas;
		}
		for (String name : names) {
			coverageAreas.add(new CoverageArea(name));
		}
		return coverageAreas;
	}

	private interface ProfessionalBuilder {
		ProfessionalBuilder setId(String id);
		ProfessionalBuilder setName(String name);
		ProfessionalBuilder setPhotoUrl(String photoUrl);
		ProfessionalBuilder setExperienceSummary(String summary);
		ProfessionalBuilder setCoverageAreas(List<CoverageArea> coverageAreas);
		ProfessionalBuilder setBrand(Brand brand);
		ProfessionalBuilder setServices(List<ServiceComponent> services);
		Professional build();
	}

	private static class HairStylistBuilder implements ProfessionalBuilder {

		protected String id;
		protected String name;
		protected String photoUrl;
		protected String summary;
		protected List<CoverageArea> coverageAreas;
		protected Brand brand;
		protected List<ServiceComponent> services;

		@Override
		public ProfessionalBuilder setId(String id) {
			this.id = id;
			return this;
		}

		@Override
		public ProfessionalBuilder setName(String name) {
			this.name = name;
			return this;
		}

		@Override
		public ProfessionalBuilder setPhotoUrl(String photoUrl) {
			this.photoUrl = photoUrl;
			return this;
		}

		@Override
		public ProfessionalBuilder setExperienceSummary(String summary) {
			this.summary = summary;
			return this;
		}

		@Override
		public ProfessionalBuilder setCoverageAreas(List<CoverageArea> coverageAreas) {
			this.coverageAreas = coverageAreas;
			return this;
		}

		@Override
		public ProfessionalBuilder setBrand(Brand brand) {
			this.brand = brand;
			return this;
		}

		@Override
		public ProfessionalBuilder setServices(List<ServiceComponent> services) {
			this.services = services;
			return this;
		}

		@Override
		public Professional build() {
			return new HairStylist(id, name, photoUrl, summary, coverageAreas, brand, services);
		}
	}

	private static class MakeupArtistBuilder extends HairStylistBuilder {
		@Override
		public Professional build() {
			return new MakeupArtist(id, name, photoUrl, summary, coverageAreas, brand, services);
		}
	}

	private static class ManicuristBuilder extends HairStylistBuilder {
		@Override
		public Professional build() {
			return new Manicurist(id, name, photoUrl, summary, coverageAreas, brand, services);
		}
	}
}
