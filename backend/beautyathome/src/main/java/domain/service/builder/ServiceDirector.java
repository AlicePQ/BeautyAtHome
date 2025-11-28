package domain.service.builder;

import java.util.List;

import domain.service.ServiceComponent;

public class ServiceDirector {

    private final ServiceBuilder builder;

    public ServiceDirector(ServiceBuilder builder) {
        this.builder = builder;
    }

    public ServiceComponent constructService(String name,
                                             String description,
                                             double price,
                                             int duration,
                                             List<String> imageUrls) {
        builder.reset();
        builder.setName(name);
        builder.setDescription(description);
        builder.setPrice(price);
        builder.setDuration(duration);
        if (imageUrls != null) {
            imageUrls.forEach(builder::addImage);
        }
        return builder.getResult();
    }
}
