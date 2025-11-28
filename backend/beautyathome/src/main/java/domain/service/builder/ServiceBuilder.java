package domain.service.builder;

import domain.service.ServiceComponent;

public interface ServiceBuilder {

    void reset();

    void setName(String name);

    void setDescription(String description);

    void setPrice(double price);

    void setDuration(int durationMinutes);

    void addImage(String url);

    ServiceComponent getResult();
}
