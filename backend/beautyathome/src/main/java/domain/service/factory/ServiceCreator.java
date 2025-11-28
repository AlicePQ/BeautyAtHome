package domain.service.factory;

import java.util.Collections;
import java.util.List;

import domain.service.ServiceComponent;
import domain.service.image.ImageReference;

public abstract class ServiceCreator {

    public ServiceComponent createService(String name,
                                          String description,
                                          double price,
                                          int duration,
                                          List<ImageReference> images) {
        List<ImageReference> safeImages = images == null ? Collections.emptyList() : images;
        return instantiate(name, description, price, duration, safeImages);
    }

    protected abstract ServiceComponent instantiate(String name,
                                                     String description,
                                                     double price,
                                                     int duration,
                                                     List<ImageReference> images);
}
