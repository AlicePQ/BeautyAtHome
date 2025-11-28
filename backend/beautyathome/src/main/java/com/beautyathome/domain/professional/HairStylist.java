package main.java.com.beautyathome.domain.professional;



import main.java.com.beautyathome.domain.service.ServiceComponent;

import java.util.List;

public class HairStylist implements Professional {

    private String id;
    private String name;
    private String photoUrl;
    private String experienceSummary;
    private List<CoverageArea> coverageAreas;
    private Brand brand;
    private List<ServiceComponent> services;

    public HairStylist(String id,
                       String name,
                       String photoUrl,
                       String experienceSummary,
                       List<CoverageArea> coverageAreas,
                       Brand brand,
                       List<ServiceComponent> services) {
        this.id = id;
        this.name = name;
        this.photoUrl = photoUrl;
        this.experienceSummary = experienceSummary;
        this.coverageAreas = coverageAreas;
        this.brand = brand;
        this.services = services;
    }

    @Override
    public String getId() { return id; }

    @Override
    public String getName() { return name; }

    @Override
    public String getPhotoUrl() { return photoUrl; }

    @Override
    public String getExperienceSummary() { return experienceSummary; }

    @Override
    public List<CoverageArea> getCoverageAreas() { return coverageAreas; }

    @Override
    public Brand getBrand() { return brand; }

    @Override
    public List<ServiceComponent> getServicesOffered() { return services; }
}

