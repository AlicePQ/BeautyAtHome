package main.java.com.beautyathome.domain.professional;


import main.java.com.beautyathome.domain.service.ServiceComponent;

import java.util.List;

public interface Professional {

    String getId();
    String getName();
    String getPhotoUrl();
    String getExperienceSummary();

    List<CoverageArea> getCoverageAreas();

    Brand getBrand();

    List<ServiceComponent> getServicesOffered();
}
