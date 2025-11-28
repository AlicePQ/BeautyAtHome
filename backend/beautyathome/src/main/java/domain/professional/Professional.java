package domain.professional;


import java.util.List;

import domain.service.ServiceComponent;

public interface Professional {

    String getId();
    String getName();
    String getPhotoUrl();
    String getExperienceSummary();

    List<CoverageArea> getCoverageAreas();

    Brand getBrand();

    List<ServiceComponent> getServicesOffered();
}
